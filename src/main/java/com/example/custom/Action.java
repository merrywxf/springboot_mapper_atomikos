package com.example.custom;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

/**
 * 自定义注解仿照{@link @MapperScan}实现简单功能,还可以自定义扩展, 用于定义扫描包入口
 * CustomConfig类{@code com.example.custom.CustomConfig} 用于解析Action注解
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(CustomConfig.class)
public @interface Action {

	@AliasFor("basePackages")
	String[] value() default {};

	/**
	 * Base packages to scan for annotated components.
	 * <p>
	 * {@link #value} is an alias for (and mutually exclusive with) this
	 * attribute.
	 * <p>
	 * Use {@link #basePackageClasses} for a type-safe alternative to
	 * String-based package names.
	 */
	@AliasFor("value")
	String[] basePackages() default {};
}
