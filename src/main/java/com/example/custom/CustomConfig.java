package com.example.custom;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;

/**
 * 用于解析{@link @Action} 扫描传入的包,将包下的类注入到spring 容器中 
 * The BeanDefinitionRegistrat must be used with @Import annotation
 * 实现ImportBeanDefinitionRegistrar接口,则该类可以用于 @Import
 * 仿照{@code org.mybatis.spring.annotation.MapperScannerRegistrar}写的
 */
public class CustomConfig implements ImportBeanDefinitionRegistrar, ResourceLoaderAware {

	private ResourceLoader resourceLoader;

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

		AnnotationAttributes annoAttrs = AnnotationAttributes
				.fromMap(importingClassMetadata.getAnnotationAttributes(Action.class.getName()));
		CustomScan scanner = new CustomScan(registry);
		// this check is needed in Spring 3.1
		if (resourceLoader != null) {
			scanner.setResourceLoader(resourceLoader);
		}
		List<String> basePackages = new ArrayList<String>();
		for (String pkg : annoAttrs.getStringArray("basePackages")) {
			if (StringUtils.hasText(pkg)) {
				if (pkg.contains(",")) {
					String[] bs = pkg.split(",");
					for (String s : bs) {
						basePackages.add(s);
					}
				} else {
					basePackages.add(pkg);
				}
			}
		}
		//重新定义扫描后的bean判断标准
		scanner.registerFilters();
		//剩下交给spring扫描器做并注入到容器中
		scanner.doScan(StringUtils.toStringArray(basePackages));

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

}
