package br.com.vivia.wishlister.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.vivia.wishlister.security.SecurityConfig;

public class LoginControllerTests {
	@Autowired
	private RestTemplate restTemplate = new RestTemplate();
	
	@Test
	public void contextLoads() {
		assertThat(restTemplate).isNotNull();
	}
	
	@Test
	public void oauthAuthenticateHasBody() {
		assertThat(restTemplate
				.getForEntity(UriComponentsBuilder.fromUriString(SecurityConfig.URL_AUTHENTICATE)
						.queryParam("client_id", SecurityConfig.CLIENT_ID).queryParam("response_type", "code")
						.queryParam("redirect_uri", SecurityConfig.REDIRECT_URI).toUriString(), String.class)
				.hasBody());
	}
	
	
}
