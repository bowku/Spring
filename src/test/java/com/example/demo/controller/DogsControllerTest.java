package com.example.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.example.demo.domain.Dogs;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:dogs-schema.sql", "classpath:dogs-data.sql"}, executionPhase=ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class DogsControllerTest {

	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Test
	void testCreate() throws Exception{
		Dogs testPerson = new Dogs(null,"John",40,"huska");
		String testPersonAsJSON = this.mapper.writeValueAsString(testPerson);
		RequestBuilder req = post("/create").contentType(MediaType.APPLICATION_JSON).content(testPersonAsJSON);
		Dogs testCreatedPerson = new Dogs (3,"John",40,"huska");
		String testCreatedPersonAsJSON = this.mapper.writeValueAsString(testCreatedPerson);
		
		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkBody = content().json(testCreatedPersonAsJSON);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
		
	}
	
	@Test
	void testAllTest() throws Exception{
		RequestBuilder req = get("/getAll");
		
		List<Dogs> testPeeps = List.of(new Dogs(1,"John",70,"huska"),new Dogs(2,"Anna",70,"huskaaa"));
		String json = this.mapper.writeValueAsString(testPeeps);
		
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void TestByID() throws Exception{
		RequestBuilder req = get("/get/1");
		Dogs testCreatedPerson = new Dogs(1,"John",70,"huska");
		String testCreatedPersonAsJSON = this.mapper.writeValueAsString(testCreatedPerson);
		
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(testCreatedPersonAsJSON);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void TestRemove() throws Exception{
		
		RequestBuilder req = delete("/remove/1");
		
		ResultMatcher checkStatus = status().isNoContent();
		
		this.mvc.perform(req).andExpect(checkStatus);
	}
	
	
	@Test
	void testUpdate() throws Exception {

		Dogs testCreatedPerson = new Dogs (null,"George",40,"huska");
		String testCreatedPersonAsJSON = this.mapper.writeValueAsString(testCreatedPerson);
		RequestBuilder req = put("/replace/1").contentType(MediaType.APPLICATION_JSON).content(testCreatedPersonAsJSON);
		Dogs CreatedPerson = new Dogs (1,"George",40,"huska");
		String CreatedPersonAsJSON = this.mapper.writeValueAsString(CreatedPerson);
		
		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().json(CreatedPersonAsJSON);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
		
	}
	
	@Test
	void testByName() throws Exception{
		
		RequestBuilder req = get("/getByName/John");
		List<Dogs> testPeeps = List.of(new Dogs(1,"John",70,"huska"));
		String json = this.mapper.writeValueAsString(testPeeps);
		
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);

		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void testByAge() throws Exception{
		
		RequestBuilder req = get("/getByAge/70");
		List<Dogs> testPeeps = List.of(new Dogs(1,"John",70,"huska"),new Dogs(2,"Anna",70,"huskaaa"));
		String json = this.mapper.writeValueAsString(testPeeps);
		
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);

		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testToString() {
		Dogs dog = new Dogs(1,"Jonah",24,"CS");
		dog.toString();
	}
	
	
}
