package com.example.custom;

import java.io.IOException;
import java.util.Set;

import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

public class CustomScan extends ClassPathBeanDefinitionScanner {

	public CustomScan(BeanDefinitionRegistry registry) {
		super(registry, false);
	}

	@Override
	public Set<BeanDefinitionHolder> doScan(String... basePackages) {
		Set<BeanDefinitionHolder> set = super.doScan(basePackages);
		return set;
	}

	public void registerFilters() {
		// context:include-filter
		// 此标签的含义是：在其扫描到的所有类中，全部自动加上注解并纳入Spring容器中
		//用于判断是否添加了spring的bean注解
		addIncludeFilter(new TypeFilter() {
			@Override
			public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory)
					throws IOException {
				return true;
			}
		});
		// context:exclude-filter
		// 此标签的含义是：排除扫描到的所有类，不纳入Spring容器中。
		// exclude package-info.java
		// addExcludeFilter(new TypeFilter() {
		// @Override
		// public boolean match(MetadataReader metadataReader,
		// MetadataReaderFactory metadataReaderFactory)
		// throws IOException {
		// String className = metadataReader.getClassMetadata().getClassName();
		// return className.endsWith("package-info");
		// }
		// });
	}
}
