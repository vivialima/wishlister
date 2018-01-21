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
import br.com.vivia.wishlister.model.ResponseCheckins;
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
	
	public User getDetailsOfUser(String accessToken, String userId){
		String urlCheckin = "https://api.foursquare.com/v2/users/"+userId;
		User self = 
				restTemplate.getForObject(
				UriComponentsBuilder.fromUriString(urlCheckin)
				.queryParam("oauth_token", accessToken)
				.queryParam("v", VERSIONING_DATE)
				.build(false)
				.toUriString(), 
		User.class);
		System.out.println("self "+self.toString());
		return self;
	}
	
	public List<Recent> getRecentsCheckins(String accessToken){
		List<Recent> recentsCheckins = new ArrayList<>();
		String urlCheckin = "https://api.foursquare.com/v2/checkins/recent/";
		try {
			System.out.println("-----recents");
			String uri = UriComponentsBuilder
					.fromUriString(urlCheckin)
					.queryParam("limit", "20")
					.queryParam("afterTimestamp", "123456")
					.queryParam("oauth_token", accessToken).
					queryParam("v", VERSIONING_DATE).
					build(false).
					toUriString();
			System.out.println(uri);
			ResponseEntity<ResponseCheckins> responseEntity = 
					restTemplate.exchange(uri, HttpMethod.GET, null,
					new ParameterizedTypeReference<ResponseCheckins>() {
					});
			
			if (responseEntity.hasBody() && responseEntity.getBody().getResponse() != null
					&& responseEntity.getBody().getResponse().getRecent()!=null) {
				recentsCheckins = responseEntity.getBody().getResponse().getRecent();
				System.out.println("recents size "+recentsCheckins.size());
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE,this.getClass().getName()+" [getRecentsCheckins]", e.getMessage());
		}
		return recentsCheckins;
	}
	

//	public String getVenues(String accessToken){
//		GET https://api.foursquare.com/v2/venues/VENUE_ID/photos
//			String urlCheckin = "https://api.foursquare.com/v2/users/"+userId;
//		User self = 
//				restTemplate.getForObject(
//				UriComponentsBuilder.fromUriString(urlCheckin)
//				.queryParam("oauth_token", accessToken)
//				.queryParam("v", VERSIONING_DATE)
//				.build(false)
//				.toUriString(), 
//		User.class);
//		System.out.println("self "+self.toString());
//
//		return "";
//	}
}
