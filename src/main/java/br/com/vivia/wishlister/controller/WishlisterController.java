package br.com.vivia.wishlister.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.vivia.wishlister.model.Recent;
import br.com.vivia.wishlister.model.User;
import br.com.vivia.wishlister.service.AuthenticationService;
import br.com.vivia.wishlister.service.WishlisterService;

@Controller
public class WishlisterController {
	
	Logger logger;
	@Autowired
    private AuthenticationService authenticationService;
	@Autowired
    private WishlisterService wishlisterService;

	
	@RequestMapping("/wishlister")
	public String getAuthenticateCode(@RequestParam("code") String codeAuthentication, Model model){
		User user = wishlisterService.getDetailsUser(authenticationService.getAcessToken(codeAuthentication),"self");
		model.addAttribute(user);		
		List<Recent> recents =	wishlisterService.getRecentsCheckins(authenticationService.getAcessToken(codeAuthentication));
		model.addAttribute("recents",recents);
//		wishlisterService.getVenuesPhotos(authenticationService.getAcessToken(codeAuthentication),"4d0428c09d33a143e64ab978");
		return "wishlister";
	}

	
	
}
