package com.sevenone.sevenfb.dao;

import java.util.List;

import com.sevenone.sevenfb.model.Notification;

public interface NotificationDao extends GenericDao<Notification, Long>{
	
	void saveNotifi(Notification noti);
	
	List<Notification> getNotifi4User(long userid);

	Notification getByUuid(String notiUUid);

	Notification getInvited(String notiType, Long whoDid, long notiTo, int notiToType);

	List<Notification> getNotifi4Team(long teamid);

}
