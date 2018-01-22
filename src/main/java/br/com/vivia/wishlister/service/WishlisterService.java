package br.com.vivia.wishlister.service;

import static br.com.vivia.wishlister.security.SecurityConfig.VERSIONING_DATE;

import java.util.ArrayList;
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

import br.com.vivia.wishlister.model.Photos;
import br.com.vivia.wishlister.model.Recent;
import br.com.vivia.wishlister.model.RecentCheckin;
import br.com.vivia.wishlister.model.ResponseBody;
import br.com.vivia.wishlister.model.User;

@Service
public class WishlisterService {

	@Autowired
	RestTemplate restTemplate;
	Logger logger;
	
	public User getDetailsUser(String accessToken, String userId){
		String uriUser = "https://api.foursquare.com/v2/users/"+userId;
		User user = null;
		try {
			ResponseBody response = 
					restTemplate.getForObject(
					UriComponentsBuilder.fromUriString(uriUser)
					.queryParam("oauth_token", accessToken)
					.queryParam("v", VERSIONING_DATE)
					.build(false)
					.toUriString(), 
					ResponseBody.class);
			
			if (response!=null && response.getResponse() != null ) {
				user =response.getResponse().getUser();
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE,this.getClass().getName()+" [getDetailsUser]", e.getMessage());
		}
		return user;
	}
	
	public List<Recent> getRecentsCheckins(String accessToken){
		List<Recent> recents = new ArrayList<>();
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
			
			ResponseEntity<ResponseBody> responseEntity = 
					restTemplate.exchange(uri, HttpMethod.GET, null,
					new ParameterizedTypeReference<ResponseBody>() {
					});
			
			if (responseEntity.hasBody() && responseEntity.getBody().getResponse() != null
					&& responseEntity.getBody().getResponse().getRecent()!=null) {
				recents = responseEntity.getBody().getResponse().getRecent();
			}
			addVenuePhotoToRecents(accessToken,recents);
			
		} catch (Exception e) {
			logger.log(Level.SEVERE,this.getClass().getName()+" [getRecentsCheckins]", e.getMessage());
		}
		return recents;
	}
	
	public void addVenuePhotoToRecents(String accessToken, List<Recent> recentsCheckins){
		try {
		   Photos photos = null;
		   for (Recent recent : recentsCheckins) {
			 photos = getVenuesPhotos(accessToken,recent.getVenue().getId());
			 recent.getVenue().setPhoto(photos.getFirstItem().getUri());
		   }
		} catch (Exception e) {
			logger.log(Level.SEVERE,this.getClass().getName()+" [addVenuePhotoToRecents]", e.getMessage());
		}
	}
	
	public Photos getVenuesPhotos(String accessToken, String venueId){
	    String urlCheckin = "https://api.foursquare.com/v2/venues/"+venueId+"/photos";
		Photos venuePhotos = null;
		try {
			System.out.println("-----getVenuesPhotos");
			String uri = 
					UriComponentsBuilder.fromUriString(urlCheckin)
					.queryParam("group", "venue")
					.queryParam("ofset", 100)
					.queryParam("limit", 100)
					.queryParam("oauth_token", accessToken)
					.queryParam("v", VERSIONING_DATE)
					.build(false)
					.toUriString();
			System.out.println("uri "+uri);
			ResponseEntity<ResponseBody> responseEntity = 
					restTemplate.exchange(uri, HttpMethod.GET, null,
					new ParameterizedTypeReference<ResponseBody>() {
					});
			
			if (responseEntity.hasBody() && responseEntity.getBody().getResponse() != null
					&& responseEntity.getBody().getResponse().getPhotos()!=null) {
				venuePhotos = responseEntity.getBody().getResponse().getPhotos();
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE,this.getClass().getName()+" [getVenuesPhotos]", e.getMessage());
		}
		return venuePhotos;
	}
}
