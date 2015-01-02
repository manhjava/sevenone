package com.sevenone.sevenfb.model.builder;

import com.sevenone.sevenfb.model.Invitation;
import com.sevenone.sevenfb.model.User;

public class InvitationBuilder {
	private Invitation invitation = new Invitation();
	
	public static InvitationBuilder getBuilder() {
        return new InvitationBuilder();
    }
	
	
	public InvitationBuilder id(long id) {
		invitation.setId(id);
		return this;
	}
	
	public InvitationBuilder status(String status) {
		invitation.setStatus(status);
		return this;
	}
	
	public InvitationBuilder teamid(long teamid) {
		invitation.setTeamid(teamid);
		return this;
	}
	
	public InvitationBuilder userid(long userid) {
		invitation.setUserid(userid);
		return this;
	}
	
	public InvitationBuilder invitor(long invitor) {
		invitation.setInvitor(invitor);
		return this;
	}
	
	public InvitationBuilder invitorUser(User invitorUser) {
		invitation.setInvitorUser(invitorUser);
		return this;
	}
	
	public Invitation build() {
        return invitation;
    }

}
