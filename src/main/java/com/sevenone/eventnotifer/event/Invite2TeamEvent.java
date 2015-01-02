package com.sevenone.eventnotifer.event;

import com.sevenone.sevenfb.ApplicationText;
import com.sevenone.sevenfb.model.Team;
import com.sevenone.sevenfb.model.User;

public class Invite2TeamEvent extends PlayerEvent {
	
	public Invite2TeamEvent(User noti2, User whoNoti, Team team) {
		super(noti2, whoNoti);
		this.toTeam = team;
	}

	Team toTeam;

	public String getMessage() {
		//Ban nhan duoc loi de nghi tham gia doi bong <a href="applicationURL/teaminfo?teamid=1">manchester</a> tu <a href="applicationURL/playerinfo?playerid=1">mr valgal</a>
		String teamName = toTeam.getName();
		String inveteName = whoDid().getFullName();
		String message = ApplicationText.INVITE_MES;
		message = message.replaceFirst("teamname", teamName);
		message = message.replaceFirst("invitorname", inveteName);
		return message;
	}

	public User whoDid() {
		return whoNotifer;
	}

	public User notiferTo() {
		return notiferTo;
	}

}
