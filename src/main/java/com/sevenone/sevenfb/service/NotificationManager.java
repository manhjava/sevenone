package com.sevenone.sevenfb.service;

import java.util.List;

import com.sevenone.sevenfb.model.Notification;

public interface NotificationManager extends GenericManager<Notification, Long>{
	
	void saveNotifi(Notification noti);
	List<Notification> getNotifi4User(long userid);
	List<Notification> getNotifi4Team(long teamid);
	Notification getByUuid(String notiUUid);
	Notification getInvited(String notiType, Long whoDid, long notiTo, int notiToType);

}
