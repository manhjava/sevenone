package com.sevenone.eventnotifer.subscri;

import com.sevenone.eventnotifer.event.Event;
import com.sevenone.eventnotifer.event.JoinTeamEvent;
import com.sevenone.eventnotifer.services.EventService;


public class MaillingSystem extends Subscriber {
	public MaillingSystem() {
		EventService.instance().subscribe(JoinTeamEvent.class, null, this);//filter is null
	}

	public void inform(Event event) {
		// Assumes that this subscriber has only subscribed to FaultEvent
		String message = event.getMessage();
		// Respond to the event
		System.out.println("Critical Fault Event occurred:" + message);
	}
}