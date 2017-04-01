package com.example.custom;

import java.lang.annotation.Annotation;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		Annotation[] annotations = bean.getClass().getClass().getAnnotations();
		for (Annotation a : annotations) {
			System.out.println(beanName + "===annotationType==" + a.annotationType());
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		Annotation[] annotations = bean.getClass().getClass().getAnnotations();
		for (Annotation a : annotations) {
			System.out.println(beanName + "===annotationType==" + a.annotationType());
		}
		return bean;
	}

}
