package com.sevenone.sevenfb.dao.hibernate;

import java.util.List;

import javax.persistence.Table;

import org.hibernate.Query;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.sevenone.sevenfb.dao.UserDao;
import com.sevenone.sevenfb.model.Team;
import com.sevenone.sevenfb.model.User;

/**
 * This class interacts with Hibernate session to save/delete and
 * retrieve User objects.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 *   Modified by <a href="mailto:dan@getrolling.com">Dan Kibler</a>
 *   Extended to implement Acegi UserDetailsService interface by David Carter david@carter.net
 *   Modified by <a href="mailto:bwnoll@gmail.com">Bryan Noll</a> to work with
 *   the new BaseDaoHibernate implementation that uses generics.
 *   Modified by jgarcia (updated to hibernate 4)
*/
@Repository("userDao")
public class UserDaoHibernate extends GenericDaoHibernate<User, Long> implements UserDao, UserDetailsService {

    /**
     * Constructor that sets the entity to User.class.
     */
    public UserDaoHibernate() {
        super(User.class);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<User> getUsers() {
        Query qry = getSession().createQuery("from User u order by upper(u.username)");
        return qry.list();
    }

    /**
     * {@inheritDoc}
     */
    public User saveUser(User user) {
        if (log.isDebugEnabled()) {
            log.debug("user's id: " + user.getId());
        }
        getSession().saveOrUpdate(user);
        // necessary to throw a DataIntegrityViolation and catch it in UserManager
        getSession().flush();
        return user;
    }

    /**
     * Overridden simply to call the saveUser method. This is happening
     * because saveUser flushes the session and saveObject of BaseDaoHibernate
     * does not.
     *
     * @param user the user to save
     * @return the modified user (with a primary key set if they're new)
     */
    @Override
    public User save(User user) {
        return this.saveUser(user);
    }

    /**
     * {@inheritDoc}
    */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //List<?> users = getSession().createCriteria(User.class).add(Restrictions.eq("username", username)).list();
    	Query exeQuery = getSession().createSQLQuery("Select u.* from app_user u where u.email = :email or u.username = :username")
				.addEntity(User.class).setParameter("email", username).setParameter("username", username);
		List<?> users = exeQuery.list();
        if (users == null || users.isEmpty()) {
            throw new UsernameNotFoundException("user '" + username + "' not found...");
        } else {
            return (UserDetails) users.get(0);
        }
    }

    /**
     * {@inheritDoc}
    */
    public String getUserPassword(Long userId) {
        JdbcTemplate jdbcTemplate =
                new JdbcTemplate(SessionFactoryUtils.getDataSource(getSessionFactory()));
        Table table = AnnotationUtils.findAnnotation(User.class, Table.class);
        return jdbcTemplate.queryForObject(
                "select password from " + table.name() + " where id=?", String.class, userId);
    }

	@Override
	public User getUserWithTeam(String username) {
		User user = (User)loadUserByUsername(username);
		Query exeQuery = getSession().createSQLQuery("Select t.* from team t, teamuser tu where tu.userid = :userid and tu.teamid = t.id")
				.addEntity(Team.class).setParameter("userid", user.getId());
		List<?> l = exeQuery.list();
		if(l.size() > 0)
		user.setTeam((Team) l.get(0));
		return user;
	}

	@Override
	public User getUserByEmail(String email) {
		Query exeQuery = getSession().createSQLQuery("Select u.* from app_user u where u.email = :email")
				.addEntity(User.class).setParameter("email", email);
		List<?> l = exeQuery.list();
		if (l.size() > 0) {
			return (User) l.get(0);
		}
		return null;
	}
}
