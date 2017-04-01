package com.example.message;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

//@Component
public class Receiver3 {
//	@RabbitListener(queues={"t-queue"})
	public void receiveMessage(String message) {
		System.out.println("系统1-3--->接收到: " + message + ">");
	}
}
