package com.sevenone.eventnotifer.subscri;

import com.sevenone.eventnotifer.event.Event;

public abstract class Subscriber {
	public abstract void inform(Event event);
}
