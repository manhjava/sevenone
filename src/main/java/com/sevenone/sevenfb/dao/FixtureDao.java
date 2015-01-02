package com.sevenone.sevenfb.dao;

import java.util.List;

import com.sevenone.sevenfb.model.Fixture;

public interface FixtureDao extends GenericDao<Fixture, Long>{

	List<Fixture> getFixtureByTeam(long teamId);

}
