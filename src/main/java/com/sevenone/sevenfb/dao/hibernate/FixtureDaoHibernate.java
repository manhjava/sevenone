package com.sevenone.sevenfb.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.sevenone.sevenfb.dao.FixtureDao;
import com.sevenone.sevenfb.model.Fixture;
@Service("fixtureDao")
public class FixtureDaoHibernate extends GenericDaoHibernate<Fixture, Long> implements FixtureDao {
	public FixtureDaoHibernate() {
		super(Fixture.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Fixture> getFixtureByTeam(long teamId) {
		Query qry = getSession().createSQLQuery("select * from fixture f where f.homeid = :teamId or f.awayid = :teamId order by f.time").addEntity(Fixture.class).setParameter("teamId", teamId);
        return qry.list();
	}
}
