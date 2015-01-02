package com.sevenone.sevenfb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sevenone.sevenfb.dao.TeamDao;
import com.sevenone.sevenfb.exception.PlayerInTwoTeamException;
import com.sevenone.sevenfb.model.Team;
import com.sevenone.sevenfb.model.User;
import com.sevenone.sevenfb.service.TeamManager;


@Service("teamManager")
public class TeamManagerImpl extends GenericManagerImpl<Team, Long> implements TeamManager{
	
	TeamDao teamDao;
	@Autowired
    public TeamManagerImpl(TeamDao teamDao) {
        super(teamDao);
        this.teamDao = teamDao;
    }
	/*@Override
	public void addUser2Team(long userid, long teamid, String role) throws PlayerInTwoTeamException {
		teamDao.addUser2Team(userid, teamid, role);
	}*/
	/*@Override
	public Team getTeambyUser(long userid) {
		Team team = teamDao.getTeambyUser(userid);
		List<User> players = teamDao.getAllPlayers(team.getId());
		List<User> caps = teamDao.getCaptains(team.getId());
		team.setPlayers(players);
		team.setCaptain(caps);
		return team;
	}*/
	@Override
	public boolean isCaptain(long userid, long teamid) {
		return teamDao.isCaptain(userid,teamid);
	}
	/*@Override
	public Team saveTeam(Team team, long userid) throws PlayerInTwoTeamException{
		return teamDao.saveTeam(team, userid);
	}*/
	/*@Override
	public Team getTeam(long teamid) {
		Team team = teamDao.get(teamid);
		List<User> players = teamDao.getAllPlayers(team.getId());
		List<User> caps = teamDao.getCaptains(team.getId());
		team.setPlayers(players);
		team.setCaptain(caps);
		return team;
	}*/
}
