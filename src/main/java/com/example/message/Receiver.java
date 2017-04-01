package com.example.message;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

//@Component
public class Receiver {
//	@RabbitListener(queues={"my-topoc"})
	public void receiveMessage(String message) {
		System.out.println("接收==========================");
		System.out.println("系统1-1--->接收到: " + message + ">");
	}
}
