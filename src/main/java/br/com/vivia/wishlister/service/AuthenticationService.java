package br.com.vivia.wishlister.service;


import java.awt.PageAttributes.MediaType;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import br.com.vivia.wishlister.model.AccessToken;
import br.com.vivia.wishlister.model.Recent;
import br.com.vivia.wishlister.model.ResponseBody;
import br.com.vivia.wishlister.model.User;
import static br.com.vivia.wishlister.security.SecurityConfig.*;

@Service
public class AuthenticationService {
 
	@Autowired
	RestTemplate restTemplate;
	Logger logger;
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	/**
	 * Calls /oauth2/authenticate and redirect to REDIRECT_URI/codeAuthentication
	 * @return String uri
	 */
	public String login() {
		String uri = "";
		try {
			uri = "redirect:" + UriComponentsBuilder.fromUriString(URL_AUTHENTICATE)
					.queryParam("client_id", CLIENT_ID).queryParam("response_type", "code")
					.queryParam("redirect_uri", REDIRECT_URI).build(false).toUriString();
		} catch (Exception e) {
			logger.log(Level.SEVERE,this.getClass().getName()+" [login]", e.getMessage());
		}
		return uri;
	}
	
	/**
	 * @param codeAuthentication
	 * @return String accessToken
	 */
	public String getAcessToken(String codeAuthentication){
		String token = "";
		try{
		AccessToken accessToken =
		restTemplate.getForObject(
				UriComponentsBuilder
				.fromUriString(URL_ACESS_TOKEN)
				.queryParam("client_id", CLIENT_ID)
				.queryParam("client_secret", CLIENT_SECRECT)
				.queryParam("grant_type", "authorization_code")
				.queryParam("redirect_uri", REDIRECT_URI)
				.queryParam("code", codeAuthentication)
				.build(false)
				.toString()
		,AccessToken.class);
		token = accessToken.getAccess_token();
		}catch (Exception e) {
			logger.log(Level.SEVERE,this.getClass().getName()+" [login]", e.getMessage());
		}
		return token;
	}
}
