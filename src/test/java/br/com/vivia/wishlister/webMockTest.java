package br.com.vivia.wishlister;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;

import br.com.vivia.wishlister.controller.WishlisterController;
import br.com.vivia.wishlister.model.Recent;
import br.com.vivia.wishlister.service.AuthenticationService;
import br.com.vivia.wishlister.service.WishlisterService;

@RunWith(SpringRunner.class)
@WebMvcTest(WishlisterController.class)
public class webMockTest {

    @MockBean
    private WishlisterService wishlisterService;
    @MockBean
	private AuthenticationService authenticationService;

	private Recent recent = null;

	@Before
	public void beforeEachTest() {
		recent = Mockito.mock(Recent.class);
		wishlisterService = Mockito.mock(WishlisterService.class);
		authenticationService = Mockito.mock(AuthenticationService.class);

		List<Recent> recents = new ArrayList<>();
		recents.add(recent);
	}

	@Test
	public void addWishlist() {
		assertEquals(0, recent.getId());
		Mockito.verify(wishlisterService).addRecent(recent);
		assertEquals(1, wishlisterService.getRecents().size());
	}

	
}
