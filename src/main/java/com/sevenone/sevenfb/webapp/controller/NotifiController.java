package com.sevenone.sevenfb.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sevenone.sevenfb.Constants;
import com.sevenone.sevenfb.model.Notification;
import com.sevenone.sevenfb.service.NotificationManager;

@Controller
public class NotifiController extends BaseFormController {
	
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
	
	@RequestMapping(value = "notificationHandler", method = RequestMethod.GET)
	public String notificationHandler(Model m, HttpServletRequest request) {
		
		String notiUUid = request.getParameter("notiUUid");
		
		Notification noti = notificationManager.getByUuid(notiUUid);
		String notiType = noti.getNotiType();
		if(Constants.INVITE_TO_TEAM.equalsIgnoreCase(notiType)) {
			long teamid = new Long(request.getParameter("teamid"));
			String agree = request.getParameter("");
		}
		
		return "";
	}
}
