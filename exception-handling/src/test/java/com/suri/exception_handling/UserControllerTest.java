package com.suri.exception_handling;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;

import com.suri.exception_handling.controller.UserController;
import com.suri.exception_handling.model.User;
import com.suri.exception_handling.service.UserService;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@WebMvcTest(UserController.class)
public class UserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	 @MockBean
	    private UserService userService;
	 
//	 @TestConfiguration
//	 static class TestConfig{
//		 
//		 @Bean
//		 public UserService userService() {
//			 return Mockito.mock(UserService.class);
//		 }}
		 
		 @Test
		 void testGetUser() throws Exception {
			 Mockito.when(userService.getUser(1L)).thenReturn(
					    new User(1L, "Suri", "suri@gmail.com", "vizag", "male")
					);
			 
			 mockMvc.perform(get("/api/user/1"))
			 .andExpect(status().isOk())
			 .andExpect(jsonPath("$.name").value("Suri"));
			 
		 }
		 
		 
		 
		 
	 }

