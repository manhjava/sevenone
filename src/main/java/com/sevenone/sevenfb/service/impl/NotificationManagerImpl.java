package com.sevenone.sevenfb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sevenone.sevenfb.dao.NotificationDao;
import com.sevenone.sevenfb.model.Notification;
import com.sevenone.sevenfb.service.NotificationManager;

@Service("notificationManager")
public class NotificationManagerImpl extends GenericManagerImpl<Notification, Long> implements NotificationManager{
	
	NotificationDao notifiDao;
	@Autowired
    public NotificationManagerImpl(NotificationDao notifiDao) {
        super(notifiDao);
        this.notifiDao = notifiDao;
    }

	@Override
	public void saveNotifi(Notification noti) {
		notifiDao.saveNotifi(noti);
		
	}

	@Override
	public List<Notification> getNotifi4User(long userid) {
		return notifiDao.getNotifi4User(userid);
	}

	@Override
	public Notification getByUuid(String notiUUid) {
		return notifiDao.getByUuid(notiUUid);
	}

	@Override
	public Notification getInvited(String notiType, Long whoDid, long notiTo, int notiToType) {
		return notifiDao.getInvited(notiType, whoDid, notiTo, notiToType);
	}

	@Override
	public List<Notification> getNotifi4Team(long teamid) {
		return notifiDao.getNotifi4Team(teamid);
	}

}
