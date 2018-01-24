package br.com.vivia.wishlister;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.then;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.vivia.wishlister.controller.LoginController;
import br.com.vivia.wishlister.controller.WishlisterController;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WishlisterApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WishlisterApplicationTest {

	@Autowired
	private LoginController loginController;

	@Autowired
	private WishlisterController wishlistController;
	
	@LocalServerPort
	private int port;

	/*
	 * Assert that the context is creating your controller you could add an assertion
	 * 
	 */
	@Test
	public void loginControllerLoads() {
		assertThat(loginController).isNotNull();
	}
	
	@Test
	public void wishlisterControllerLoads() {
		assertThat(wishlistController).isNotNull();
	}
}
