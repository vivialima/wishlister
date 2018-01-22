package br.com.vivia.wishlister.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.vivia.wishlister.model.Recent;
import br.com.vivia.wishlister.model.RecentCheckin;
import br.com.vivia.wishlister.model.User;
import br.com.vivia.wishlister.repository.RecentsRepository;
import br.com.vivia.wishlister.repository.UserRepository;
import br.com.vivia.wishlister.repository.WishlistRepository;
import br.com.vivia.wishlister.service.AuthenticationService;
import br.com.vivia.wishlister.service.WishlisterService;

@Controller
public class WishlisterController {
	
	@Autowired
    private AuthenticationService authenticationService;
	@Autowired
    private WishlisterService wishlisterService;
	@Autowired
	private RecentsRepository recentsCheckinsRepository;
	@Autowired
	private WishlistRepository wishlistRepository;
	@Autowired
	private UserRepository userRepository;
   @Autowired
	private HttpSession httpSession;
	
	@RequestMapping("/wishlister")
	public String getAuthenticateCode(@RequestParam("code") String codeAuthentication, Model model){
		User user = wishlisterService.getDetailsUser(authenticationService.getAcessToken(codeAuthentication), "self");  
		httpSession.setAttribute("user", user);
		List<Recent> recents =	wishlisterService.getRecentsCheckins(authenticationService.getAcessToken(codeAuthentication));
		httpSession.setAttribute("recents", recents);
		
		
		System.out.println("User get session "+httpSession.getAttribute("recents"));
		
		model.addAttribute(user);		
		model.addAttribute("wishlist", httpSession.getAttribute("wishlist"));
		model.addAttribute("recents",recents);
		return "wishlister";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "add/{recent}", method = RequestMethod.GET)
	public String add(@RequestParam("recent") Recent recent, Model model) {
	System.out.println("addWishlist "+recent);
		
//		try {
//			List<Recent> wishlist =  (List<Recent>) httpSession.getAttribute("wishlist");
//			List<Recent> recents =  (List<Recent>) httpSession.getAttribute("recents");
//
//			wishlist.add(recents.)
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		
		model.addAttribute("wishlist", httpSession.getAttribute("wishlist"));
		model.addAttribute("recents", httpSession.getAttribute("recents"));
		model.addAttribute("user",httpSession.getAttribute("user"));		
		return "wishlister";

	}
}
