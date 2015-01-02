package com.sevenone.sevenfb.webapp.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sevenone.sevenfb.ApplicationText;
import com.sevenone.sevenfb.Constants;
import com.sevenone.sevenfb.exception.PlayerInTwoTeamException;
import com.sevenone.sevenfb.model.Notification;
import com.sevenone.sevenfb.model.Team;
import com.sevenone.sevenfb.model.User;
import com.sevenone.sevenfb.service.NotificationManager;
import com.sevenone.sevenfb.util.DateUtil;
import com.sevenone.sevenfb.util.JsonUtil;
import com.sevenone.sevenfb.webapp.util.RequestUtil;


@Controller
public class TeamController extends BaseFormController {
	
	/** The notification manager. */
	private NotificationManager notificationManager;

	/**
	 * Sets the notification manager.
	 *
	 * @param notificationManager the new notification manager
	 */
	@Autowired
	public void setNotificationManager(NotificationManager notificationManager) {
		this.notificationManager = notificationManager;
	}
	
	@ModelAttribute
	@RequestMapping(value="createteam", method=RequestMethod.GET)
    public String showForm(Model m) {
		System.out.println("show create form");
		m.addAttribute("team", new Team());
    	return "createteam";
    }
	
	@RequestMapping(value="teammanager/{teamid}", method=RequestMethod.GET)
    public String showTeamManagerPage(Model m, @PathVariable String teamid) {
		Team team = null;
		long tid = -1;
		try {
			tid = Long.valueOf(teamid);
		} catch (NumberFormatException nfe) {
			tid = -1;
		}
		
		if(tid > 0) {
			team = teamManager.get(tid);
		} else {
			User currentUser = getCurrentUser();
			team = currentUser.getTeam();
			if(team == null) {
				team = new Team();
				m.addAttribute("team", team);
				return "createteam";
			}
		}
		m.addAttribute("team", team);
		return "teammanager";
    }
	
	@RequestMapping(value="all-teams", method=RequestMethod.GET)
    public String showAllTeams(Model m) {
    	List<Team> teams = teamManager.getAll();
		m.addAttribute("teams", teams);
		return "all-teams";
    }
	
	
	@RequestMapping(value="createteam", method=RequestMethod.POST)
	public String submitForm(@ModelAttribute Team team, BindingResult result, Model m) {
		if(result.hasErrors()) {
            return "createteam";
        }
		User currentUser = getCurrentUser();
		team.setJoinedDate(DateUtil.todayDate());
		team.addPlayer(currentUser);
		Team added = teamManager.save(team);
		m.addAttribute("team", added);
		return "teammanager";
	}
	
	@RequestMapping(value="addPlayer", method=RequestMethod.GET, params = { "userid" })
	public void addPlayer2Team(HttpServletResponse response, @RequestParam(value = "userid") String pUserid, HttpServletRequest request) {
		String returnMes = ApplicationText.ADD_PLAYER_SUCCESS;
		User currentUser = getCurrentUser();
		Team currentTeam = currentUser.getTeam();
		if (currentTeam == null) {
			returnMes = ApplicationText.ADD_TEAM_FIRST;
		}
		long userid = new Long(pUserid);
		User userToAdd = userManager.get(userid);
		currentTeam.addPlayer(userToAdd);
		//Must have C role to add player
		boolean isCaptain = "C".equalsIgnoreCase(currentUser.getTeamRole());
		if(!isCaptain) {
			//TODO: temp comment out
			//returnMes = ApplicationText.ADD_BUT_CAPTAIN;
		}
		
		//TODO: Notification for user.
		/*Notification noti = new Notification();
		String uuid = UUID.randomUUID().toString();
		noti.setUuid(uuid);
		String message = ApplicationText.ADD_TO_TEAM;
		message = message.replaceFirst("teamname", currentTeam.getName());
		message = message.replaceFirst("invitorname", currentUser.getFullName());
		String responseUrl = RequestUtil.getAppURL(request) + "/notificationHandler?notiUUid=" + uuid + "&teamid=" + teamid;//appurl/notificationHandler?notiid=uuid;
		message = message.replaceFirst("responseUrl", responseUrl);
		noti.setNotiContent(message);
		noti.setNotiStatus(Constants.NOTIFI_STATUS_NEW);
		noti.setNotiTo(userid);
		noti.setNotiToType(Constants.NOTIFI_TO_PLAYER);
		noti.setNotiType(Constants.ADD_TO_TEAM);
		noti.setWhoDid(currentUser.getId());
		notificationManager.save(noti);*/
		
		
		teamManager.save(currentTeam);
		
		response.setContentType("application/json");
		PrintWriter out;
		try {
			out = response.getWriter();
			String jsonStr = "{\"returnMes\": \""+ returnMes +"\"}";
			out.print(jsonStr);
		} catch (IOException e) {
			log.error("Exception: response out error when get user");
			e.printStackTrace();
		}
	}
}
