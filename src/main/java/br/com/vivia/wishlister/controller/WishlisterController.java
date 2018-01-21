package br.com.vivia.wishlister.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.vivia.wishlister.service.AuthenticationService;

@Controller
public class WishlisterController {
	
	Logger logger;
	@Autowired
    private AuthenticationService foursquare;
	
	@RequestMapping(value = "/wishlister", method = RequestMethod.GET)
	public String getAuthenticateCode(@RequestParam("code") String codeAuthentication){
		foursquare.getDetailsOfUser(foursquare.getAcessToken(codeAuthentication),"self");
		foursquare.getRecentsCheckins(foursquare.getAcessToken(codeAuthentication));
		return "wishlister";
	}

	
	
}
