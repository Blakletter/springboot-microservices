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
import java.time.LocalDate;

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
	Event event = new Event(2, "Test", LocalDate.now().toString());
	//This is our second mock event we are going to use to test updating back and forth between events!
	Event event2 = new Event(3, "Test2", LocalDate.now().toString());
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
		String requestBody = new ObjectMapper().valueToTree(event).toString();
		this.mockMvc.perform(
				post("/createevent")
						.content(requestBody)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
		)
				.andDo(print()).andExpect(status().isCreated());
	}

	@Test
	@Order(9)
	public void testFindEvents() throws Exception {
		this.mockMvc.perform(
				post("/requestevents")
						.param("userId", Long.toString(event.getUserId()))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
		)
				.andDo(print()).andExpect(status().isOk());
	}

	@Test
	@Order(10)
	public void testDeleteEvents() throws Exception {
		this.mockMvc.perform(
				delete("/deleteevents")
						.param("userId", Long.toString(event.getUserId()))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
		)
				.andDo(print()).andExpect(status().isOk());
	}

	@Test
	@Order(11)
	public void testUpdateEvents() throws Exception {
		String requestBody = new ObjectMapper().valueToTree(event2).toString();
		this.mockMvc.perform(
				post("/updateevent")
						.param("eventId", Long.toString(15)) //event.getEventId()
						.content(requestBody)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
		)
				.andDo(print()).andExpect(status().isCreated());
	}
}

