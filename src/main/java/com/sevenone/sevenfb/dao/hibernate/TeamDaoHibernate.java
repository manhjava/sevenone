package com.sevenone.sevenfb.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.sevenone.sevenfb.dao.TeamDao;
import com.sevenone.sevenfb.exception.PlayerInTwoTeamException;
import com.sevenone.sevenfb.model.Team;
import com.sevenone.sevenfb.model.User;

@Service("teamDao")
public class TeamDaoHibernate extends GenericDaoHibernate<Team, Long> implements TeamDao {

	public TeamDaoHibernate() {
		super(Team.class);
	}

	/*@Override
	public void addUser2Team(long userid, long teamid, String role) throws PlayerInTwoTeamException {
		Team t = getTeambyUser(userid);
		if (t != null) {
			throw new PlayerInTwoTeamException("Not allow player in two team");
		}
		String query = "Insert into teamuser (userid, teamid, role) values (:userid, :teamid, :role)";
		Query exeQuery = getSession().createSQLQuery(query).setParameter("userid", userid).setParameter("teamid", teamid).setParameter("role", role);
		exeQuery.executeUpdate();
	}*/

	/*@Override
	public Team getTeambyUser(long userid) {
		Query exeQuery = getSession().createSQLQuery("Select t.* from team t, teamuser tu where tu.userid = :userid and tu.teamid = t.id")
				.addEntity(Team.class).setParameter("userid", userid);
		List<?> l = exeQuery.list();
		if (l.size() > 0) {
			return (Team) l.get(0);
		}
		return null;
	}*/

	@Override
	public boolean isCaptain(long userid, long teamid) {
		Query exeQuery = getSession().createSQLQuery("Select * from teamuser tu where tu.userid = :userid and tu.teamid = :teamid and tu.role = 'C'")
				.setParameter("userid", userid).setParameter("teamid", teamid);
		List<?> l = exeQuery.list();
		if (l.size() > 0) {
			return true;
		}
		return false;
	}

	/*@Override
	public Team saveTeam(Team team, long userid) throws PlayerInTwoTeamException {
		Team t = getTeambyUser(userid);
		if (t != null) {
			throw new PlayerInTwoTeamException("Not allow player in two team");
		}
		Team sevedTeam = save(team);
		addUser2Team(userid, sevedTeam.getId(), "C");
		return sevedTeam;
	}*/


	@SuppressWarnings("unchecked")
	public List<User> getAllPlayers(long teamid) {
		Query exeQuery = getSession().createSQLQuery("select u.* from app_user u, teamuser tu where u.id = tu.userid and tu.teamid = :teamid")
				.addEntity(User.class).setParameter("teamid", teamid);
		return (List<User>) exeQuery.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getCaptains(long teamid) {
		Query exeQuery = getSession().createSQLQuery("select u.* from app_user u, teamuser tu where u.id = tu.userid and tu.teamid = :teamid and tu.role = 'C'")
				.addEntity(User.class).setParameter("teamid", teamid);
		return (List<User>) exeQuery.list();
	}

}
