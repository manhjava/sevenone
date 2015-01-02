package com.sevenone.sevenfb.service;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sevenone.sevenfb.model.Fixture;
import com.sevenone.sevenfb.model.Team;

public class FixtureManagerTest extends BaseManagerTestCase {
    @Autowired
    private FixtureManager mgr;
    @Autowired
    private TeamManager teamMgr;

    @Test
    public void testGetFixture() throws Exception {
    	long id = 4l;
    	Fixture fixture = mgr.get(id);
    	System.out.println(fixture.getHome().getName() + " vs " + fixture.getAway().getName() + " at " + fixture.getStadium());
    }
    
    @Test
    public void testFixtureByTeam() throws Exception {
    	long teamid = 1l;
    	List<Fixture> fixtures = mgr.getFixtureByTeam(teamid);
    	for(Fixture fixture : fixtures)
    	System.out.println(fixture.getHome().getName() + " vs " + fixture.getAway().getName() + " at " + fixture.getStadium());
    }
    
    @Test
    public void testSaveFixture() throws Exception {
    	Fixture fix = new Fixture();
    	Team home = teamMgr.get(1l);
    	Team away = teamMgr.get(2l);
    	fix.setHomeid(1l);
    	fix.setAwayid(2l);
//    	fix.setHome(home);
//    	fix.setAway(away);
    	fix.setResult("1-0");
    	fix.setStadium("camp nou");
    	fix.setTime(new Date());
    	Fixture fixture = mgr.save(fix);
    	//System.out.println(fixture.getHome().getName() + " vs " + fixture.getAway().getName() + " at " + fixture.getStadium());
    }
}
