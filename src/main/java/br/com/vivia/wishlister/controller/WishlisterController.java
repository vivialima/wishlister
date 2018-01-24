package br.com.vivia.wishlister.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import br.com.vivia.wishlister.model.User;
import br.com.vivia.wishlister.service.AuthenticationService;
import br.com.vivia.wishlister.service.WishlisterService;

@Controller
public class WishlisterController {
	
	@Autowired
    private AuthenticationService authenticationService;
	@Autowired
    private WishlisterService wishlisterService;
   @Autowired
	private HttpSession httpSession;
	
	@RequestMapping("wishlister")
	public String getAuthenticationCode(@RequestParam("code") String codeAuthentication, Model model){		
		model.addAttribute(getUser(codeAuthentication));		
		model.addAttribute("recents",wishlisterService.getRecentsCheckinsFriends(authenticationService.getAcessToken(codeAuthentication)));
		model.addAttribute("wishlist", wishlisterService.getWishlist());
		return "wishlister";
	}

	public User getUser(String codeAuthentication) {
		User user = wishlisterService.getDetailsUser(authenticationService.getAcessToken(codeAuthentication), "self");  
		httpSession.setAttribute("user", user);
		httpSession.setAttribute("codeAuthentication", codeAuthentication);
		return user;
	}

	@RequestMapping(path = "add/{id}", method = RequestMethod.GET)
	public String addWishlist(@PathVariable String id, Model model) {
		model.addAttribute("wishlist",wishlisterService.addWishlist(id));
		model.addAttribute("recents", wishlisterService.getRecents());
		model.addAttribute("user",httpSession.getAttribute("user"));		
        return "redirect:/wishlister?code="+httpSession.getAttribute("codeAuthentication");
	}
	

	@RequestMapping(path = "remove/{id}", method = RequestMethod.GET)
	public String removeWishlist(@PathVariable String id, Model model) {
		System.out.println("ID "+id);
		model.addAttribute("wishlist",wishlisterService.removeWishlist(id));
		model.addAttribute("recents", wishlisterService.getRecents());
		model.addAttribute("user",httpSession.getAttribute("user"));		
        return "redirect:/wishlister?code="+httpSession.getAttribute("codeAuthentication");
	}
	
	}

	

