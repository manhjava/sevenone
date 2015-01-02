package com.sevenone.sevenfb.service.impl;

import javax.xml.ws.Endpoint;
 
//Endpoint publisher
public class WSHelloWorldPublisher{
 
	public static void main(String[] args) {
	   Endpoint.publish("http://localhost:8080/sevenone/hello", new WSHelloWorldImpl());
    }
 
}
