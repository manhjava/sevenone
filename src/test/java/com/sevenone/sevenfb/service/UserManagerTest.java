package com.sevenone.sevenfb.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sevenone.sevenfb.Constants;
import com.sevenone.sevenfb.model.Team;
import com.sevenone.sevenfb.model.User;

public class UserManagerTest extends BaseManagerTestCase {
    private Log log = LogFactory.getLog(UserManagerTest.class);
    @Autowired
    private UserManager mgr;
    @Autowired
    private RoleManager roleManager;
    private User user;

    @Test
    public void testGetUser() throws Exception {
        user = mgr.get(1l);
        String frofile = user.getFbprofileUrl();
        String image = user.getFbimageUrl();
        System.out.println(frofile + "_  " + image);
    	long time = System.currentTimeMillis();
        System.out.println(System.currentTimeMillis() - time);
        assertNotNull(user);
        
        log.debug(user);
        assertEquals(1, user.getRoles().size());
    }
    
    @Test
    public void testGetUserTeam() throws Exception {
        //user = mgr.getUserByUsername("trinh.manh");
    	long time = System.currentTimeMillis();
        user = mgr.getUserWithTeam("manh.trinh");
        System.out.println(System.currentTimeMillis() - time);
        Team t = user.getTeam();
        if(t != null) {
        	System.out.println("Team: " + t.getName());
        } else {
        	System.out.println("Team null: ");
        }
        assertNotNull(user);
        
        log.debug(user);
        assertEquals(1, user.getRoles().size());
    }

    @Test
    public void testSaveUser() throws Exception {
        user = mgr.getUserByUsername("user");
        user.setPhoneNumber("303-555-1212");

        log.debug("saving user with updated phone number: " + user);

        user = mgr.saveUser(user);
        assertEquals("303-555-1212", user.getPhoneNumber());
        assertEquals(1, user.getRoles().size());
    }

    @Test
    public void testAddAndRemoveUser() throws Exception {
        user = new User();

        // call populate method in super class to populate test data
        // from a properties file matching this class name
        user = (User) populate(user);

        user.addRole(roleManager.getRole(Constants.USER_ROLE));

        user = mgr.saveUser(user);
        assertEquals("john", user.getUsername());
        assertEquals(1, user.getRoles().size());

        log.debug("removing user...");

        mgr.removeUser(user.getId().toString());

        try {
            user = mgr.getUserByUsername("john");
            fail("Expected 'Exception' not thrown");
        } catch (Exception e) {
            log.debug(e);
            assertNotNull(e);
        }
    }
    
    @Test
    public void testGetAll() throws Exception {
    	long time = System.currentTimeMillis();
        List<User> found = mgr.getAll();
        System.out.println(System.currentTimeMillis() - time);
        log.debug("Users found: " + found);
        assertEquals(3, found.size());
    }
    
    @Test
    public void testUserbyEmail() throws Exception {
    	String email = "trc@gmil.com";
    	User u =  mgr.getUserByEmail(email);
    	System.out.println(u.getConfirmPassword());
        List<User> found = mgr.getAll();
        log.debug("Users found: " + found);
        assertEquals(3, found.size());
    }


}
