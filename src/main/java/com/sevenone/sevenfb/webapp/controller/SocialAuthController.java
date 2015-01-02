package com.sevenone.sevenfb.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


//@Controller
//@RequestMapping("/auth/facebook*")
public class SocialAuthController {
	
//	@ModelAttribute
//    @RequestMapping(method = RequestMethod.GET)
    public String showForm() {
		System.out.println("facebook auth begin...");
        return "/auth/facebook";
    }

}
