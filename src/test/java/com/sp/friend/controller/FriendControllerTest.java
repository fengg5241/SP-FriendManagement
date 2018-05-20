package com.sp.friend.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FriendControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void connectShouldReturnTailoredMessage() throws Exception {

		String[] emails = { "a@gmail.com", "b@gmail.com" };
		this.mockMvc.perform(post("/connect").param("friends", emails)).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.success").value(true));
	}

	@Test
	public void retrieveFriendsPositiveCase() throws Exception {

		String[] emails = { "b@gmail.com", "c@gmail.com" };
		this.mockMvc.perform(get("/retrieveFriends").param("email", "a@gmail.com")).andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$.success").value(true))
				.andExpect(jsonPath("$.friends").value(emails)).andExpect(jsonPath("$.count").value(emails.length));
	}

	@Test
	public void retrieveCommonFriendsPositiveCase() throws Exception {

		String[] emails = { "a@gmail.com", "b@gmail.com" };
		String[] commonFriends = { "c@gmail.com" };
		this.mockMvc.perform(post("/retrieveCommonFriends").param("friends", emails)).andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$.success").value(true))
				.andExpect(jsonPath("$.success").value(true)).andExpect(jsonPath("$.friends").value(commonFriends))
				.andExpect(jsonPath("$.count").value(emails.length));
	}

	@Test
	public void subscribePositiveCase() throws Exception {

		this.mockMvc.perform(post("/subscribe").param("requestor", "a@gmail.com").param("target", "b@gmail.com"))
				.andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.success").value(true));
	}

	@Test
	public void blockPositiveCase() throws Exception {

		this.mockMvc.perform(post("/block").param("requestor", "a@gmail.com").param("target", "b@gmail.com"))
				.andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.success").value(true));
	}

	@Test
	public void retrieveActiveFriendsPositiveCase() throws Exception {

		String[] emails = { "c@gmail.com" };
		this.mockMvc
				.perform(get("/retrieveActiveFriends").param("sender", "a@gmail.com").param("text",
						"Hello World! kate@example.com"))
				.andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.success").value(true))
				.andExpect(jsonPath("$.recipients").value(emails));
	}
}
