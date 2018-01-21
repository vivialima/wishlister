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


	public static void main(String[] args) {
		SpringApplication.run(WishlisterApplication.class, args);
	}

}
