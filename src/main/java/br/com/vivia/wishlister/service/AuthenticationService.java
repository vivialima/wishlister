package br.com.vivia.wishlister.service;


import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.vivia.wishlister.security.SecurityConfig;
@Service
public class AuthenticationService {
 
	@Autowired
	RestTemplate restTemplate;
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	/**
	 * 
	 * @return userCode for acess token
	 */
	public String login(){		
	return "redirect:" +  UriComponentsBuilder.fromUriString(SecurityConfig.URL_AUTHENTICATE)
				.queryParam("client_id", SecurityConfig.CLIENT_ID).queryParam("response_type", "code")
				.queryParam("redirect_uri", SecurityConfig.REDIRECT_URI)
				.build(false)
				.toUriString();
	}

	/**
	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			
			//Test api
			String url = "https://api.foursquare.com/v2/venues/explore";
			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
					.queryParam("client_id", CLIENT_ID)
					.queryParam("client_secret", CLIENT_SECRECT)
					.queryParam("v", "20170801")
					.queryParam("ll", "40.7243,-74.0018")
					.queryParam("query", "coffee")
					.queryParam("limit", "1");

			System.out.println("GET " + builder.toUriString());
			ResponseEntity<String> response = restTemplate.getForEntity(builder.toUriString(), String.class);
			System.out.println("RESPONSE " + response.toString());

			//Test Authenticate
			String urlAuth = "https://foursquare.com/oauth2/authenticate";
			UriComponentsBuilder builderAuthenticate = UriComponentsBuilder.fromUriString(urlAuth)
					.queryParam("client_id", CLIENT_ID).queryParam("response_type", "code")
					.queryParam("redirect_uri", YOUR_REGISTERED_REDIRECT_URI);

			System.out.println("URI AUTH " + builderAuthenticate.toUriString());
			response = restTemplate.getForEntity(builder.toUriString(), String.class);
			System.out.println("RESPONSE " + response.toString());

			//Token
			String urlAcessToken = "https://foursquare.com/oauth2/access_token";
			
			builderAuthenticate = UriComponentsBuilder
					.fromUriString(urlAcessToken)
					.queryParam("client_id", CLIENT_ID)
					.queryParam("client_secret", CLIENT_SECRECT)
					.queryParam("grant_type", "authorization_code")
					.queryParam("redirect_uri", YOUR_REGISTERED_REDIRECT_URI)
					.queryParam("code", CODE_AUTH)
					;
			
			System.out.println("GET urlAcessToken " + builderAuthenticate.build(false));
			
			response = restTemplate.getForEntity(builderAuthenticate.build(false).toUriString(), String.class);
			System.out.println("RESPONSE " + response.toString());
			
			AccessToken accessToken =
					restTemplate.getForObject(builderAuthenticate.build(false).toUriString(), AccessToken.class);
			System.out.println(accessToken.toString());
			
			
			//Test GET Checkins
			String user = "self";
			String urlCheckin = "https://api.foursquare.com/v2/users/"+user+"/checkins";
			builderAuthenticate = UriComponentsBuilder.fromUriString(urlCheckin)
					.queryParam("oauth_token", accessToken.getAccess_token())
					.queryParam("v",VERSIONING_DATE);

			System.out.println("GET urlAcessToken " + builderAuthenticate.build(false));
			response = restTemplate.getForEntity(builderAuthenticate.build(false).toUriString(), String.class);

		};
	}
	**/
}
