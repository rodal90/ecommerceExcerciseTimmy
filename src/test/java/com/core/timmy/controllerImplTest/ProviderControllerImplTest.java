package com.core.timmy.controllerImplTest;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.core.timmy.data.model.Provider;
import com.core.timmy.serviceImpl.ProviderServiceImpl;

@SpringBootTest
@AutoConfigureMockMvc
class ProviderControllerImplTest {
	
	@MockBean
	private ProviderServiceImpl providerService;
	
	@Autowired
	private MockMvc mockMvc;
	
	private Provider provider;
	private List<Provider> providerList;
	
	@BeforeEach
	public void setup(){
		this.provider = new Provider(1L,"Tech Innovators","CIF12345A","ESB9845645645", "lo, 14", "65561561561","coli@techo.com",null,"1565151");
		Provider provider2=  new Provider(2L,"Tech vators","CIF15651445A","ESB5646545445", "li, 27", "6555454541","coy@teco.com",null,"15545641");
		this.providerList = List.of(this.provider, provider2);
	}
	

	@Test
	void testProviderViewGet() {
		fail("Not yet implemented");
	}

	@Test
	@WithMockUser(username="ana", password = "anaPass", roles = "USER")
	void testProviderListGet() throws Exception {
		//fail("Not yet implemented");
		
		// precondition:
		when(providerService.findAll()).thenReturn(this.providerList);
		
		//action:
		ResultActions response = mockMvc.perform(get("/providerListGet"));
		
		//verify the output
		response
		        .andDo(print())
		        .andExpect(status().isOk())
		        .andExpect(content().string(containsString("ana")))
		        .andExpect(content().string(containsString("Tech Innovators")))
		        .andExpect(content().string(containsString("Tech vators")));
		
	}

	@Test
	void testProviderDeleteGet() {
		fail("Not yet implemented");
	}

	@Test
	void testProviderDeleteConfirmed() {
		fail("Not yet implemented");
	}

	@Test
	void testProviderUpdateGet() {
		fail("Not yet implemented");
	}

	@Test
	void testProviderUpdatePost() {
		fail("Not yet implemented");
	}

	@Test
	void testProviderAddGet() {
		fail("Not yet implemented");
	}

	@Test
	void testProviderAddPost() {
		fail("Not yet implemented");
	}

	@Test
	void testInjectCommonAtrributesInHtmlPage() {
		fail("Not yet implemented");
	}

}
