package com.sevenone.sevenfb.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.sevenone.sevenfb.dao.NotificationDao;
import com.sevenone.sevenfb.model.Notification;

@Service("notifiDao")
public class NotificationDaoHibernate extends GenericDaoHibernate<Notification, Long> implements NotificationDao{

	public NotificationDaoHibernate() {
		super(Notification.class);
	}

	@Override
	public void saveNotifi(Notification noti) {
		getSession().saveOrUpdate(noti);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Notification> getNotifi4User(long userid) {
		String selectQuery = "select * from notification no where no.notiTo = :userid and no.notiToType = 2";
		Query selectq = getSession().createSQLQuery(selectQuery).addEntity(Notification.class).setParameter("userid", userid);
		return selectq.list();
	}

	@Override
	public Notification getByUuid(String notiUUid) {
		String selectQuery = "select * from notification no where no.uuid = :notiUUid";
		Query selectq = getSession().createSQLQuery(selectQuery).addEntity(Notification.class).setParameter("notiUUid", notiUUid);
		try {
			return (Notification) selectq.list().get(0);
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public Notification getInvited(String notiType, Long whoDid, long notiTo, int notiToType) {
		String selectQuery = "select * from notification no where no.notiType = :notiType and no.whoDid = :whoDid and no.notiTo = :notiTo and no.notiToType = :notiToType";
		Query selectq = getSession().createSQLQuery(selectQuery).addEntity(Notification.class).setParameter("notiType", notiType).
				setParameter("whoDid", whoDid).setParameter("notiTo", notiTo).setParameter("notiToType", notiToType);
		try {
			return (Notification) selectq.list().get(0);
		} catch(Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Notification> getNotifi4Team(long teamid) {
		String selectQuery = "select * from notification no where no.notiTo = :teamid and no.notiToType = 1";
		Query selectq = getSession().createSQLQuery(selectQuery).addEntity(Notification.class).setParameter("userid", teamid);
		return selectq.list();
	}

}
