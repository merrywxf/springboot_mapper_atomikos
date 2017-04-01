package com.example.message;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

//@Component
public class Receiver2 {
//	@RabbitListener(queues = "my-queue")
	public void receiveMessage(String message) {
		System.out.println("系统1-2---->接收到: " + message + ">");
	}
}
