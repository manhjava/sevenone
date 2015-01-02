package com.sevenone.sevenfb.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.sevenone.sevenfb.dao.InviteDao;
import com.sevenone.sevenfb.model.Invitation;

@Service("inviteDao")
public class InviteDaoHibernate extends GenericDaoHibernate<Invitation, Long> implements InviteDao {

	public InviteDaoHibernate() {
		super(Invitation.class);
	}

	@Override
	public void saveInvite(Invitation invitation) {
		getSession().saveOrUpdate(invitation);
	}

	@Override
	public Invitation getInvitationBy(long userid, long teamid) {
		Query exeQuery = getSession().createSQLQuery("select i.* from invitation i where i.userid = :userid and i.teamid = :teamid").
				addEntity(Invitation.class).setParameter("teamid", teamid).setParameter("userid", userid);
		List<?> l = exeQuery.list();
		try {
			return (Invitation)l.get(0);
		} catch(Exception e) {
			return null;
		}
	}

}
