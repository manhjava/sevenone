package com.sevenone.sevenfb.dao;

import com.sevenone.sevenfb.model.Invitation;

public interface InviteDao extends GenericDao<Invitation, Long>{
	
	void saveInvite(Invitation invitation);
	
	Invitation getInvitationBy(long userid, long teamid);

}
