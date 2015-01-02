package com.sevenone.sevenfb.service;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sevenone.sevenfb.model.Team;
import com.sevenone.sevenfb.model.User;
//copied from UserManagerTest
public class TeamManagerTest extends BaseManagerTestCase {
    private Log log = LogFactory.getLog(TeamManagerTest.class);
    @Autowired
    private TeamManager mgr;
    private Team team;
    @Autowired
    private UserManager userMgr;

    @Test
    public void testGetTeam() throws Exception {
    	long id = 4l;
    	team = mgr.get(id);
    	Set<User> l = team.getPlayers();
    	for(User u : l) {
    		System.out.println(u.getFbprofileUrl());
    	}
        //assertNotNull(team);
        //log.debug(team);
        assertEquals("ManchesterUtd", team.getName());
    }
    
    @Test
    public void testAllTeams() throws Exception {
    	
    	List<Team> teams = mgr.getAll();
    	for(Team t : teams) {
    		if("Arsenal".equals(t.getName())) {
    			System.out.println("Arsenal");
    		}
    	}
    	assertEquals(9,teams.size());
    }
    
    @Test
    public void testIsCaptain() throws Exception {
    	boolean cap = mgr.isCaptain(10, 4);
    	
    	if(cap) {
    		log.debug("Is Captain");
    	} else {
    		log.debug("Not Captain");
    	}
    }
    
    @Test
    public void testGetTeamById() throws Exception {
    	team = mgr.get(1l);
    	log.debug(team.getName());
    	assertEquals("ManchesterUtd", team.getName());
    }
    
    @Test
    public void testAddUserToTeam() throws Exception {
    	team = mgr.get(1l);
    	log.debug("player size before: " + team.getPlayers().size());
    	User u = userMgr.get(4l);
    	u.setUsername("Ibrahim");
    	team.addPlayer(u);
    	team = mgr.save(team);
    	log.debug("player size after: " + team.getPlayers().size());
    	assertEquals("Arsenal", team.getName());
    }
    
    @Test
    public void testSaveTeam() throws Exception {
    	team = mgr.get(1l);
    	log.debug("player size before: " + team.getPlayers().size());
    	User u = userMgr.get(4l);
    	u.setUsername("Ibrahim");
    	team.addPlayer(u);
    	team = mgr.save(team);
    	log.debug("player size after: " + team.getPlayers().size());
    	assertEquals("Arsenal", team.getName());
    }
    
    @Test
    public void testSave() throws Exception {
    	Team team = new Team();
    	team.setName("Unit Test Team Name");
    	team.setJoinedDate(new Date());
    	team.setType(1);
    	team.setRegion("Hanoi");
    	team.setStadium("Le Ngoc Han");
    	team = mgr.save(team);
    	System.out.println(team.getId());
    }
}

