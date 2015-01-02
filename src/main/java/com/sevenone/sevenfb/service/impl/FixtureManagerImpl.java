package com.sevenone.sevenfb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sevenone.sevenfb.dao.FixtureDao;
import com.sevenone.sevenfb.model.Fixture;
import com.sevenone.sevenfb.service.FixtureManager;
@Service("fixtureManager")
public class FixtureManagerImpl extends GenericManagerImpl<Fixture, Long> implements FixtureManager{
	FixtureDao fixtureDao;
	@Autowired
    public FixtureManagerImpl(FixtureDao fixtureDao) {
        super(fixtureDao);
        this.fixtureDao = fixtureDao;
    }
	@Override
	public List<Fixture> getFixtureByTeam(long teamId) {
		return fixtureDao.getFixtureByTeam(teamId);
	}
}
