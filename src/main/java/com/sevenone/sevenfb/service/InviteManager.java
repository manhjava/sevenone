package com.sevenone.sevenfb.service;

import com.sevenone.eventnotifer.event.Invite2TeamEvent;
import com.sevenone.sevenfb.model.Invitation;

public interface InviteManager extends GenericManager<Invitation, Long>{
	
	void saveInvite(Invitation invitation);
	Invitation getInvitationBy(long userid, long teamid);
	void inviteJoin(Invite2TeamEvent event);

}
