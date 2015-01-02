package com.sevenone.eventnotifer.event;

import com.sevenone.sevenfb.model.User;


public abstract class PlayerEvent implements Event{
	
	
	public PlayerEvent(User noti2, User who) {
		this.notiferTo = noti2;
		this.whoNotifer = who;
	}

	User notiferTo;
	User whoNotifer;
	public abstract String getMessage();

}
