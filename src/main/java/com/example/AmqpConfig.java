package com.example;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;

/**
 * rabitMQ配置定义交换机与队列
 */
// @Component
public class AmqpConfig {

	// direct 交换机
	@Bean
	public DirectExchange myExchange() {
		DirectExchange dExchange = new DirectExchange("my-exchange", true, false);
		return dExchange;
	}

	@Bean
	public Queue wiselyQueue() {
		Queue wiselyQueue = new Queue("my-queue");
		return wiselyQueue;
	}

	@Bean
	public Queue toptic() {
		return new Queue("my-topoc", true, false, false, null);
	}

	// 绑定关系
	@Bean
	public Binding getBinding() {
		return BindingBuilder.bind(wiselyQueue()).to(myExchange()).with("my-topoc");
	}

	@Bean
	public Binding myBinding() {
		Binding binding = BindingBuilder.bind(toptic()).to(myExchange()).with("my-queue");// 将queue绑定到exchange
		return binding;
	}

	// fanout交换机（广播）
	@Bean
	public FanoutExchange myFanoutExchange() {
		FanoutExchange fExchange = new FanoutExchange("my-fanoutexchange", true, false);
		return fExchange;
	}

	// fanout绑定
	@Bean
	public Binding getBinding3() {
		return BindingBuilder.bind(wiselyQueue()).to(myFanoutExchange());
	}

	@Bean
	public Binding getBinding4() {
		return BindingBuilder.bind(toptic()).to(myFanoutExchange());
	}

	// topic交换机（广播）
	@Bean
	public TopicExchange myTopicExchange() {
		TopicExchange topicExchange = new TopicExchange("my-topicexchange", true, false);
		return topicExchange;
	}

	@Bean
	public Queue wiselyQueue_Topic() {
		Queue wiselyQueue = new Queue("t-queue");
		return wiselyQueue;
	}

	@Bean
	public Queue b_Topic() {
		Queue wiselyQueue = new Queue("n-queue");
		return wiselyQueue;
	}

	@Bean
	public Queue toptic_Topic() {
		return new Queue("t-topoc");
	}

	// topic绑定
	@Bean
	public Binding getBinding7() {
		return BindingBuilder.bind(b_Topic()).to(myTopicExchange()).with("*.*.*");
	}

	@Bean
	public Binding getBinding5() {
		return BindingBuilder.bind(wiselyQueue_Topic()).to(myTopicExchange()).with("*.*.*");
	}

	@Bean
	public Binding getBinding6() {
		return BindingBuilder.bind(toptic_Topic()).to(myTopicExchange()).with("*.*.*");
	}

}
