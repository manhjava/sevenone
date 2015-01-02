package com.sevenone.sevenfb.service;

import com.sevenone.sevenfb.exception.PlayerInTwoTeamException;
import com.sevenone.sevenfb.model.Team;

public interface TeamManager extends GenericManager<Team, Long> {
	//public void addUser2Team(long userid, long teamid, String role) throws PlayerInTwoTeamException;//ROLE: C or P
	//public Team getTeambyUser(long userid);
	public boolean isCaptain(long userid, long teamid);
	//public Team saveTeam(Team team, long userid) throws PlayerInTwoTeamException;
	//public Team getTeam(long teamid);
}
