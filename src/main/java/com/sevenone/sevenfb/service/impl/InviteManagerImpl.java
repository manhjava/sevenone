package com.sevenone.sevenfb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sevenone.eventnotifer.event.Invite2TeamEvent;
import com.sevenone.eventnotifer.event.PlayerEvent;
import com.sevenone.sevenfb.Constants;
import com.sevenone.sevenfb.dao.InviteDao;
import com.sevenone.sevenfb.model.Invitation;
import com.sevenone.sevenfb.model.Notification;
import com.sevenone.sevenfb.service.InviteManager;
import com.sevenone.sevenfb.service.NotificationManager;

@Service("inviteManager")
public class InviteManagerImpl extends GenericManagerImpl<Invitation, Long> implements InviteManager{
	InviteDao inviteDao;
	@Autowired
    public InviteManagerImpl(InviteDao inviteDao) {
        super(inviteDao);
        this.inviteDao = inviteDao;
    }
	/** The notification manager. */
	private NotificationManager notificationManager;

	/**
	 * Sets the notification manager.
	 *
	 * @param notificationManager the new notification manager
	 */
	@Autowired
	public void setNotificationManager(NotificationManager notificationManager) {
		this.notificationManager = notificationManager;
	}
	@Override
	public void saveInvite(Invitation invitation) {
		inviteDao.saveInvite(invitation);
	}
	@Override
	public Invitation getInvitationBy(long userid, long teamid) {
		return inviteDao.getInvitationBy(userid, teamid);
	}
	
	@Override
	public void inviteJoin(Invite2TeamEvent event) {
		//EventService.instance().publish(event);
		Notification noti = new Notification();
		noti.setNotiContent(event.getMessage());
		noti.setNotiStatus(Constants.NOTIFI_STATUS_NEW);
		noti.setNotiTo(event.notiferTo().getId());
		noti.setNotiToType(Constants.NOTIFI_TO_PLAYER);
		noti.setNotiType(Constants.INVITE_TO_TEAM);
		noti.setWhoDid(event.whoDid().getId());
		notificationManager.save(noti);
	}

}
