package br.com.vivia.wishlister.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.vivia.wishlister.repository.UserRepository;
import br.com.vivia.wishlister.repository.WishlistRepository;
import br.com.vivia.wishlister.service.AuthenticationService;

@Controller
public class LoginController {
	@Autowired
    private AuthenticationService authenticationService;

	@RequestMapping("/")
	public String login(){
		return "login";
	}
	

//	@RequestMapping("/")
//	public String logout(){
//		return "login";
//	}
//	
	
	@RequestMapping("/redirect")
	public String redirectFoursquareLogin(){
		return authenticationService.login();
	}

	
}
