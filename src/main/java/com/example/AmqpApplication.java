package com.example;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
/**
 * 此项目返回类型一切都是string类型省去{@code @ResponseBody}注解
 * 若想修改请看{@code com.example.custom.MappingConfig}中addHandlerMethodReturnValueHandler()方法
 * 
 * */
@SpringBootApplication
public class AmqpApplication implements CommandLineRunner {
	@Autowired
	ApplicationContext applicationContext;
	// @Autowired
	// RabbitTemplate rebbitTemplate;

	public static void main(String[] args) {
		SpringApplication.run(AmqpApplication.class, args);
		System.out.println("hello==========================");
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("hello==========================");
		String[] names = applicationContext.getBeanDefinitionNames();
		Stream.of(names).forEach(m -> {
			// System.out.println(m + "=========m======================");
		});
		// mappingConfig.afterPropertiesSet();
		// this.rebbitTemplate.convertAndSend("my-fanoutexchange",
		// "====hello=");
		// this.rebbitTemplate.convertAndSend("my-exchange","my-topoc",
		// "====hello=");
		// this.rebbitTemplate.convertAndSend("my-fanoutexchange","",
		// "====my-fanoutexchange=");
		// this.rebbitTemplate.convertAndSend("my-topicexchange","test.test.test",
		// "====my-topicexchange=");

	}

}
