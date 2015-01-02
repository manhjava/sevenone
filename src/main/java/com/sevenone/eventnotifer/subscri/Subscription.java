package com.sevenone.eventnotifer.subscri;

import com.sevenone.eventnotifer.event.Filter;


public class Subscription {
	public Subscription(Class<?> anEventType, Filter aFilter, Subscriber aSubscriber) { 
	    eventType = anEventType; 
	    filter = aFilter; 
	    subscriber = aSubscriber; 
	  }

	  public Class<?> eventType; 
	  public Filter filter; 
	  public Subscriber subscriber; 
}
