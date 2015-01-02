package com.sevenone.sevenfb.webapp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import com.sevenone.sevenfb.model.User;

@Controller
public class CommonController extends BaseFormController {
	
	@RequestMapping(value = "home", method = RequestMethod.GET)
    public String showHomeForm(WebRequest request) {
    	return "home";
    }
	
	@RequestMapping(value="my-account-details", method=RequestMethod.GET)
    public String myAccountDetails(Model m) {
    	User user = getCurrentUser();
    	m.addAttribute("user", user);
    	return "my-account-details";
		
    }
	
	@RequestMapping(value="facebooklogin", method=RequestMethod.POST)
    public void facebookLogin(Model m, HttpServletRequest request) {
		String token = request.getParameter("token");
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		User user = userManager.getUserByEmail(email);
		if(user == null) {
			System.out.println("user does not exist:");
		} else {
			System.out.println("user does exist:");
		}
    }
	
	@RequestMapping(value="updateAccountInfo", method=RequestMethod.GET)
	public void updateAccountInfo(HttpServletResponse response, HttpServletRequest request) {
		User user = getCurrentUser();
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String phone = request.getParameter("phoneNumber");
		String dob = request.getParameter("dob");
		String position = request.getParameter("position");
		
		if(!user.getUsername().equals(username)) {
			//TODO : cannot update able
		}
		
		user.setEmail(email);
		user.setPosition(position);
		user.setPhoneNumber(phone);
		user.setDob(dob);
		User updated = userManager.save(user);
		String returnMes = "update-failure";
		if(updated != null) {
			returnMes = "update-success";
		}
		response.setContentType("application/json");
		PrintWriter out;
		try {
			out = response.getWriter();
			String jsonStr = "{\"returnMes\": \""+ returnMes +"\"}";
			out.print(jsonStr);
		} catch (IOException e) {
			log.error("Exception: response out error when update user");
			e.printStackTrace();
		}
		
		
	}

}
