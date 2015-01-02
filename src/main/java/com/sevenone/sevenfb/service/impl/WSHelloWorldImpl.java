package com.sevenone.sevenfb.service.impl;

import javax.jws.WebService;

import com.sevenone.sevenfb.service.WSHelloWorld;

//Service Implementation
@WebService(endpointInterface = "com.sevenone.sevenfb.service.WSHelloWorld")
public class WSHelloWorldImpl implements WSHelloWorld{

	@Override
	public String getHelloWorldAsString(String name) {
		return "Hello World JAX-WS " + name;
	}

}
