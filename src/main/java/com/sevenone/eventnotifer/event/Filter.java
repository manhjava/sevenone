package com.sevenone.eventnotifer.event;


public interface Filter {
	public boolean apply(Event event);
}
