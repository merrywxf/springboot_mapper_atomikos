package com.example.message;

public class Msg  {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Msg() {
		super();
		this.message = "测试消息1";
	}

	public Msg(String msg) {
		super();
		this.message = msg;
	}


}
