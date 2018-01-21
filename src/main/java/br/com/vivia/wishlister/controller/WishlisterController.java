package br.com.vivia.wishlister.controller;

import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WishlisterController {
	
	Logger logger;
	
	@RequestMapping(value = "/wishlister", method = RequestMethod.GET)
	public String getAuthenticateCode(@RequestParam("code") String code){
		return "wishlister";
	}

}
