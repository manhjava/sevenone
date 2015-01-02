package com.sevenone.eventnotifer.services;

import java.util.Enumeration;
import java.util.Vector;

import com.sevenone.eventnotifer.event.Event;
import com.sevenone.eventnotifer.event.Filter;
import com.sevenone.eventnotifer.subscri.Subscriber;
import com.sevenone.eventnotifer.subscri.Subscription;


public class EventService {

	private Class<Event> eventClass;
	protected Vector<Subscription> subscriptions;

	// Prevents direct instantiation of the event service
	private EventService() {
		eventClass = Event.class;
		subscriptions = new Vector<Subscription>();
	}

	static private EventService singleton = null;

	// Provides well-known access point to singleton EventService
	static public EventService instance() {
		if (singleton == null)
			singleton = new EventService();
		return singleton;
	}

	public void publish(Event event) {
		for (Enumeration<Subscription> elems = subscriptions.elements(); elems.hasMoreElements();) {
			Subscription subscription = (Subscription) elems.nextElement();
			if (subscription.eventType.isAssignableFrom(event.getClass()) && (subscription.filter == null || subscription.filter.apply(event)))
				subscription.subscriber.inform(event);
		}
	}

	public void subscribe(Class<?> eventType, Filter filter, Subscriber subscriber) throws InvalidEventTypeException {
		if (!eventClass.isAssignableFrom(eventType))
			throw new InvalidEventTypeException();

		// Prevent duplicate subscriptions
		Subscription subscription = new Subscription(eventType, filter, subscriber);
		if (!subscriptions.contains(subscription))
			subscriptions.addElement(subscription);
	}

	public void unsubscribe(Class<?> eventType, Filter filter, Subscriber subscriber) throws InvalidEventTypeException {
		if (!eventClass.isAssignableFrom(eventType))
			throw new InvalidEventTypeException();
		subscriptions.removeElement(new Subscription(eventType, filter, subscriber));
	}

}
