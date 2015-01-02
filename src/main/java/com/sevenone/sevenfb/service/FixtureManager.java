package com.sevenone.sevenfb.service;

import java.util.List;

import com.sevenone.sevenfb.model.Fixture;

public interface FixtureManager extends GenericManager<Fixture, Long> {
	
	public List<Fixture> getFixtureByTeam(long teamId);

}
