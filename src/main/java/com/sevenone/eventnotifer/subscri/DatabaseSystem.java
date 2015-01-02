package com.sevenone.eventnotifer.subscri;

import com.sevenone.eventnotifer.event.Event;
import com.sevenone.eventnotifer.event.Invite2TeamEvent;
import com.sevenone.eventnotifer.services.EventService;
import com.sevenone.sevenfb.model.User;
import com.sevenone.sevenfb.service.NotificationManager;

public class DatabaseSystem extends Subscriber {
	
	private NotificationManager notificationManager;

	public void setNotificationManager(NotificationManager notificationManager) {
		this.notificationManager = notificationManager;
	}
	
	public DatabaseSystem() {
		EventService.instance().subscribe(Invite2TeamEvent.class, null, this);//filter is null
	}
	

	@Override
	public void inform(Event event) {
		// Assumes that this subscriber has only subscribed to FaultEvent
		String message = event.getMessage();
		User whoNoti = event.whoDid();
		User notiTo = event.notiferTo();
		boolean ex = notificationManager.exists(01L);
		System.out.println(ex);
		// Respond to the event
		System.out.println("Event Notifer System: "+message + "-----" + whoNoti.getFullName() + "----" + notiTo.getFullName());
	}

}
