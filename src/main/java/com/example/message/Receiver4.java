package com.example.message;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

//@Component
public class Receiver4 {
//	@RabbitListener(queues={"t-topoc"})
	public void receiveMessage(String message) {
		System.out.println("系统1-4--->接收到: " + message + ">");
	}
}
