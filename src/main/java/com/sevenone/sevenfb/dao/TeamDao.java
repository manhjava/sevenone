package com.sevenone.sevenfb.dao;

import java.util.List;

import com.sevenone.sevenfb.exception.PlayerInTwoTeamException;
import com.sevenone.sevenfb.model.Team;
import com.sevenone.sevenfb.model.User;

public interface TeamDao extends GenericDao<Team, Long>{
	// create read update delete
//	void createOrUpdateTeam(Team team);
//	void deleteTeam(String teamid);
//	Team getTeam(String teamid);
//	List<Team> getTeams();
//	boolean isExist(Long id);
	public List<User> getAllPlayers(long teamid);
	public List<User> getCaptains(long teamid);
	//public void addUser2Team(long userid, long teamid, String role) throws PlayerInTwoTeamException;

	//public Team getTeambyUser(long userid);

	public boolean isCaptain(long userid, long teamid);

	//public Team saveTeam(Team team, long userid) throws PlayerInTwoTeamException;
}
