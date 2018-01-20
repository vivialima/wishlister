package br.com.vivia.wishlister;

import java.net.URI;
import java.net.URLEncoder;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.vivia.wishlister.model.AccessToken;

@SpringBootApplication
public class WishlisterApplication {

	private static final String CLIENT_ID = "RMPNMTMP11BC5RMHEK3U2SLTNZ4TL0ZZWRK5XUJQLKFLAM2Y";
	private static final String CLIENT_SECRECT = "445PRNUC3PZMOSKPBNZFVDXGL0ZLQX4CYBBLMCU4DEDBWQGU";
	private static final String YOUR_REGISTERED_REDIRECT_URI = "http://localhost:8080/wishlister";
	private static final String VERSIONING_DATE = "20190801";
	private static String CODE_AUTH = "UXN1TSIPSM54T013DTYTMVXRJXMYZ1BXU45EZWNOJLBEJH5J#_=_";

	public static void main(String[] args) {
		SpringApplication.run(WishlisterApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
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
