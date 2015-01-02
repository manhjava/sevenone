package com.sevenone.sevenfb.webapp.controller;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sevenone.sevenfb.ApplicationText;
import com.sevenone.sevenfb.Constants;
import com.sevenone.sevenfb.model.Invitation;
import com.sevenone.sevenfb.model.Notification;
import com.sevenone.sevenfb.model.Team;
import com.sevenone.sevenfb.model.User;
import com.sevenone.sevenfb.model.builder.InvitationBuilder;
import com.sevenone.sevenfb.service.InviteManager;
import com.sevenone.sevenfb.service.NotificationManager;
import com.sevenone.sevenfb.webapp.util.RequestUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class InvitationController.
 */
@Controller
public class InvitationController extends BaseFormController {

	/** The invite manager. */
	private InviteManager inviteManager;
	
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

	/**
	 * Sets the invite manager.
	 *
	 * @param inviteManager the new invite manager
	 */
	@Autowired
	public void setInviteManager(InviteManager inviteManager) {
		this.inviteManager = inviteManager;
	}

	/**
	 * Invite to team.
	 *
	 * @param m the m
	 * @param pUserid the userid
	 * @return the string
	 */
	@RequestMapping(value = "inviteToTeam", method = RequestMethod.GET, params = { "userid" })
	public String inviteToTeam(Model m, @RequestParam(value = "userid") String pUserid, HttpServletRequest request) {
		
		User currentUser = getCurrentUser();
		Team currentTeam = currentUser.getTeam();
		
		if (currentTeam == null) {
			return "createteam";
		}
		long teamid = currentTeam.getId();
		long userid = new Long(pUserid);
		
		//Check ALREADY_INVITE
		
		Notification wasInvited = notificationManager.getInvited(Constants.INVITE_TO_TEAM, currentUser.getId(), userid, Constants.NOTIFI_TO_PLAYER);
		
		if(wasInvited != null) {
			m.addAttribute("message", ApplicationText.ALREADY_INVITE);
			return "join-myteam";
		}
		
		
		Set<User> players = currentTeam.getPlayers();
		for(User player : players) {
			if(player.getId() == userid) {
				m.addAttribute("message", ApplicationText.ALREADY_INTEAM);
				return "join-myteam";
			}
		}
		
		Notification noti = new Notification();
		String uuid = UUID.randomUUID().toString();
		noti.setUuid(uuid);
		String message = ApplicationText.INVITE_MES;
		message = message.replaceFirst("teamname", currentTeam.getName());
		message = message.replaceFirst("invitorname", currentUser.getFullName());
		String responseUrl = RequestUtil.getAppURL(request) + "/notificationHandler?notiUUid=" + uuid + "&teamid=" + teamid;//appurl/notificationHandler?notiid=uuid;
		message = message.replaceFirst("responseUrl", responseUrl);
		noti.setNotiContent(message);
		noti.setNotiStatus(Constants.NOTIFI_STATUS_NEW);
		noti.setNotiTo(userid);
		noti.setNotiToType(Constants.NOTIFI_TO_PLAYER);
		noti.setNotiType(Constants.INVITE_TO_TEAM);
		noti.setWhoDid(currentUser.getId());
		notificationManager.save(noti);
		m.addAttribute("message",ApplicationText.INVITE_SUCCESS);
		return "join-myteam";
	}
	
	/**
	 * Notifer response.
	 *
	 * @param m the m
	 * @param notiferId the notifer id
	 * @param responseType the response type
	 * @return the string
	 */
	@RequestMapping(value = "notiferResponse", method = RequestMethod.GET, params = { "notiferId","responseType" })
	public String notiferResponse(Model m, @RequestParam(value = "notiferId") String notiferId, @RequestParam(value = "responseType") String responseType) {
		
		/*long notiferid = new Long(notiferId);
		Invitation invitation = inviteManager.get(inviteId);

		User currentUser = getCurrentUser();
		
		if(invitation.getUserid() != currentUser.getId() || replyType == null) {
			//TODO throw invalid request exception
			m.addAttribute("error-message",ApplicationText.INVALID_REQUEST);
			return "error-page";
		}
		
		if("ACCEPT".equals(replyType)) {
			currentUser.setTeamid(invitation.getTeamid());
			userManager.save(currentUser);
			invitation.setStatus("done-accept");
		} else if ("REJECT".equals(replyType)) {
			invitation.setStatus("done-reject");
		}
		inviteManager.save(invitation);
		
		m.addAttribute("message",ApplicationText.INVITE_SUCCESS);*/

		return "join-myteam";
	}
	
	/**
	 * Emulator.
	 *
	 * @param m the m
	 * @return the string
	 */
	@RequestMapping(value = "emulator", method = RequestMethod.GET)
	public String emulator(Model m) {
		
		

//		User currentUser = getCurrentUser();
//		
//		List<Notification> noti = notificationManager.getNotifi4User(currentUser.getId());
//		
//		if(invitation.getUserid() != currentUser.getId() || replyType == null) {
//			//TODO throw invalid request exception
//			m.addAttribute("error-message",ApplicationText.INVALID_REQUEST);
//			return "error-page";
//		}
//		
//		if("ACCEPT".equals(replyType)) {
//			currentUser.setTeamid(invitation.getTeamid());
//			userManager.save(currentUser);
//			invitation.setStatus("done-accept");
//		} else if ("REJECT".equals(replyType)) {
//			invitation.setStatus("done-reject");
//		}
//		inviteManager.save(invitation);
//		
//		m.addAttribute("message",ApplicationText.INVITE_SUCCESS);
//
		return "join-myteam";
	}
	
	/**
	 * The main method.
	 *
	 * @param arg the arguments
	 */
	public static void main(String[] arg) {
		Invitation invitation = 
				InvitationBuilder.getBuilder()
				.invitor(1)
				.status("waiting")
				.teamid(2)
				.userid(3).build();
		Invitation invitation2 = new Invitation();
		
		invitation2.setInvitor(1);
		invitation2.setStatus("waiting");
		invitation2.setTeamid(2);
		invitation2.setUserid(3);
		
		System.out.println(invitation);
	}

	/**
	 * Submit invite to team.
	 *
	 * @param m the m
	 * @param jsonString the json string
	 * @return the string
	 */
	@RequestMapping(value = "submitInviteToTeam", method = RequestMethod.GET, params = { "jsonValue" })
	public String submitInviteToTeam(Model m, @RequestParam(value = "jsonValue") String jsonString) {

		User curent = getCurrentUser();
		JSONObject obj;
		String playerid = "";
		String teamid = "";
		try {
			obj = new JSONObject(jsonString);
			playerid = obj.getString("playerid");
			teamid = obj.getString("teams");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Invitation invitation = new Invitation();
		invitation.setInvitor(curent.getId());
		invitation.setStatus("waiting");
		invitation.setTeamid(new Long(teamid));
		invitation.setUserid(new Long(playerid));

		inviteManager.save(invitation);

		// create invite to player
		Notification noti = new Notification();
		noti.setNotiContent("Bạn nhận được lời đề nghị tham gia đội bóng Manchester từ thành viên " + curent.getFullName());
		noti.setNotiStatus("waiting");
		noti.setNotiTo(new Long(playerid));
		noti.setNotiType("joinrequest");
		notificationManager.save(noti);

		// TODO : send notification

		return "join-myteam";
	}

}
