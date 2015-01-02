package com.sevenone.eventnotifer.event;

import com.sevenone.sevenfb.model.User;

public interface Event {
	String getMessage();
	User whoDid();
	User notiferTo();
}
