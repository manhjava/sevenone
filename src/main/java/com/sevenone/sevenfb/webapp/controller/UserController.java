package com.sevenone.sevenfb.webapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sevenone.sevenfb.Constants;
import com.sevenone.sevenfb.dao.SearchException;
import com.sevenone.sevenfb.model.User;
import com.sevenone.sevenfb.service.UserManager;
import com.sevenone.sevenfb.util.JsonUtil;


/**
 * Simple class to retrieve a list of users from the database.
 * <p/>
 * <p>
 * <a href="UserController.java.html"><i>View Source</i></a>
 * </p>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
@Controller
public class UserController {
    private UserManager userManager = null;
    protected final transient Log log = LogFactory.getLog(getClass());

    @Autowired
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    @RequestMapping(value="/admin/users*", method = RequestMethod.GET)
    public ModelAndView handleRequest(@RequestParam(required = false, value = "q") String query) throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(Constants.USER_LIST, userManager.search(query));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(userManager.getUsers());
        }
        return new ModelAndView("admin/userList", model.asMap());
    }
    
    @RequestMapping(value="all-players", method=RequestMethod.GET)
    public String showAllPlayers(Model m) {
    	List<User> users = userManager.getAll();
		m.addAttribute("users", users);
		return "all-players";
    }
    
    @RequestMapping(value="all-players-ViewPlayerDetail", method=RequestMethod.GET, params = { "username" })
    public void showPlayersInfo(HttpServletResponse response, @RequestParam(value = "username") String username) {
    	
    	User user = null;
		try {
			user = userManager.getUserByUsername(username);
		} catch (UsernameNotFoundException unfe) {
			log.error("UsernameNotFoundException: User " + username + " does not exist");
		} catch (Exception e) {
			log.error("Exception: error when get user");
			e.printStackTrace();
		}

		response.setContentType("application/json");
		PrintWriter out;
		try {
			out = response.getWriter();
			String jsonStr = JsonUtil.userToJson(user);
			out.print(jsonStr);
		} catch (IOException e) {
			log.error("Exception: response out error when get user");
			e.printStackTrace();
		}
		
    }
}
