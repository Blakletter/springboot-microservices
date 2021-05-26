package com.cancerup.sqlaccesslayer;
import com.cancerup.sqlaccesslayer.models.Contact;
import com.cancerup.sqlaccesslayer.models.Event;
import com.cancerup.sqlaccesslayer.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SqlAccessLayerTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private SqlAccessLayer controller;

	@Test
	@Order(1)
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

	//Here is our mock user we are going to user
	User user = new User("test@gmail.com", "password", "Test", "USER");
	//This is our mock event we are going to use
	Event event = new Event(2, "Test", new java.sql.Date(System.currentTimeMillis()));
	//This is our global contact we are create/add. The user 44 is a test user (Mickey Mouse)
	Contact contact = new Contact(new User(44), "Donald", "Duck");


	//CONTACT TESTS
	@Test
	@Order(2)
	public void testCreateUser() throws Exception {
		String requestBody = new ObjectMapper().valueToTree(user).toString();
		this.mockMvc.perform(
				post("/createuser")
						.contentType(MediaType.APPLICATION_JSON)
						.content(requestBody)
						.accept(MediaType.APPLICATION_JSON)
		)
				.andDo(print()).andExpect(status().isCreated());
	}

	@Test
	@Order(3)
	public void testConflictedUser() throws Exception {
		String requestBody = new ObjectMapper().valueToTree(user).toString();
		this.mockMvc.perform(
				post("/createuser")
						.contentType(MediaType.APPLICATION_JSON)
						.content(requestBody)
						.accept(MediaType.APPLICATION_JSON)
		)
				.andDo(print()).andExpect(status().isConflict());
	}





	@Test
	@Order(4)
	public void testDeleteUser() throws Exception {
		String requestBody = new ObjectMapper().valueToTree(user).toString();
		this.mockMvc.perform(
				delete("/deleteuser")
						.param("email", user.getEmail())
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
		)
				.andDo(print()).andExpect(status().isOk());
	}

	@Test
	@Order(5)
	public void testFindUser() throws Exception {
		this.mockMvc.perform(
				delete("/deleteuser")
						.param("email", user.getEmail())
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
		)
				.andDo(print()).andExpect(status().isNotFound());
	}


	//EVENT CONTACTS
	@Test
	@Order(6)
	public void testAddContact() throws Exception {
		//The event
		String requestBody = new ObjectMapper().valueToTree(contact).toString();
		this.mockMvc.perform(
				post("/createcontact")
						.content(requestBody)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
		)
				.andDo(print()).andExpect(status().isCreated());
	}

	//EVENT CONTACTS
	@Test
	@Order(7)
	public void testDeleteContact() throws Exception {
		//The event
		this.mockMvc.perform(
				delete("/deletecontact")
						.param("firstname", contact.getFirstName())
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
		)
				.andDo(print()).andExpect(status().isOk());
	}

	@Test
	@Order(8)
	public void testEvents() throws Exception {
		//The event
		//String requestBody = new ObjectMapper().valueToTree(event).toString();
		String requestBody2 = "{ \"userId\":22, \"eventName\":\"Meeting\", \"eventDate\":\"2021-05-20\" }";
		this.mockMvc.perform(
				post("/createevent")
						.contentType(MediaType.APPLICATION_JSON)
						.content(requestBody2)
						.accept(MediaType.APPLICATION_JSON)
		)
				.andDo(print()).andExpect(status().isCreated());
	}



}

