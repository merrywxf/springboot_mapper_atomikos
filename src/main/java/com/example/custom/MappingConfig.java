
package com.example.custom;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * <p>
 * 通过此类将自定义的requestmapping注入到spring中 可以实现简单的自定义路由 当然自己还可以扩展
 * </p>
 * 解析{@link @RequestMapping}
 * 
 * <p>
 * 修改{@code org.springframework.messaging.handler.invocation.HandlerMethodReturnValueHandler}接口
 * </p>
 * 增加自定义的返回类型{@code MyRequestResponseBodyMethodProcessor}
 * 只是简单的复制{@code RequestResponseBodyMethodProcessor} 的解析方式 将判断类型直接返回为true
 */
@Component
@Action(basePackages = "com.example.controller,com.example.service")
public class MappingConfig implements InitializingBean {

	@Autowired
	ApplicationContext applicationContext;
	@Autowired
	RequestMappingHandlerAdapter requestMappingHandlerAdapter;
	@Autowired
	RequestMappingHandlerMapping requestMappingHandlerMapping;

	@Override
	public void afterPropertiesSet() {
		try {
			// 添加路由信息
			addRequestMapping();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// 改变返回值responseBody
		addHandlerMethodReturnValueHandler();
		String[] names = applicationContext.getBeanDefinitionNames();
		Stream.of(names).forEach(m -> {
			System.out.println(m + "=========m======================");
		});
		// 获取requestMapping中有哪些url
		getRequestMapping();
	}

	private void addHandlerMethodReturnValueHandler() {
		List<HandlerMethodReturnValueHandler> list = new ArrayList<>();
		list.add(new MyRequestResponseBodyMethodProcessor(this.requestMappingHandlerAdapter.getMessageConverters()));
		// 此方法将去掉spring自定义的返回值类型
		// 此项目中只使用string作为返回类型
		// 还可以调用其他方法将自己的增加到原有的返回值类型中
		// List<HandlerMethodReturnValueHandler> returnValueHandlers =
		// this.requestMappingHandlerAdapter.getReturnValueHandlers();
		// returnValueHandlers.add(e)
		this.requestMappingHandlerAdapter.setReturnValueHandlers(list);
	}

	public void getRequestMapping() {
		Map<RequestMappingInfo, HandlerMethod> handlerMethods = this.requestMappingHandlerMapping.getHandlerMethods();
		handlerMethods.forEach((m, n) -> {
			PatternsRequestCondition prc = m.getPatternsCondition();
			Set<String> patterns = prc.getPatterns();
			for (String uStr : patterns) {
				System.out.println(uStr + "=========url===============");
			}
			System.out.println(n.getBeanType() + "===================");

		});
	}

	private void addRequestMapping() throws SecurityException, ClassNotFoundException {
		Set<String> set = PackageUtil.findPackageClass("com.example.controller");
		for (String clazz : set) {
			String clazzName = clazz.substring(clazz.lastIndexOf(".") + 1);
			char[] cs = clazzName.toCharArray();
			cs[0] += 32;
			clazzName = String.valueOf(cs);
			Class<?> forName = Class.forName(clazz);
			String methodName = "";
			String path;
			Method[] methods = forName.getMethods();
			for (Method m : methods) {
				methodName = m.getName();
				path = "/" + clazzName + "/" + methodName;
				// 判断方法名,确定为自定义名称和请求方法
				if (methodName.contains("insert") || methodName.contains("update") || methodName.contains("save")
						|| methodName.contains("delete")) {
					requestMappingHandlerMapping.registerMapping(getMappingForMethod("post", path), clazzName, m);
				} else if (methodName.contains("test") || methodName.contains("query") || methodName.contains("get")
						|| methodName.contains("find")) {
					requestMappingHandlerMapping.registerMapping(getMappingForMethod("get", path), clazzName, m);
				}
			}
		}
	}

	/**
	 * 只是简单的实现{@code @ResponseBody}
	 * 若想更多请看
	 * {@code org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping}中
	 * </p>
	 * getMappingForMethod(Method method, Class<?> handlerType)方法
	 */
	private RequestMappingInfo getMappingForMethod(String requestMethod, String path) {
		RequestMethod method = RequestMethod.GET;
		if ("post".equals(requestMethod)) {
			method = RequestMethod.POST;
		}
		return RequestMappingInfo.paths(path).methods(method).build();
	}

}
