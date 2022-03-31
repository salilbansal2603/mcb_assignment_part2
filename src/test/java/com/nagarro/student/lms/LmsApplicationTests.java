package com.nagarro.student.lms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dto.JwtRequest;
import dto.MarksDTO;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LmsApplicationTests {

	private String accessToken = "Bearer ";

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	public void setup() throws Exception {
		accessToken = accessToken + getToken();
	}

	public String getToken() throws Exception {
		String uri = "/authenticate";
		JwtRequest request = new JwtRequest();
		request.setUsername("salilbansal");
		request.setPassword("password");
		String inputJson = mapToJson(request);
		MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		String content = mvcResult.getResponse().getContentAsString();
		JSONObject jsonObj = new JSONObject(content);

		return jsonObj.getString("token");
	}

	@Test
	public void testGetMarksByStudentIdPositive() throws Exception {
		String uri = "/marks-by-student/1";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)
				.header("Authorization", accessToken)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		MarksDTO[] marks = mapFromJson(content, MarksDTO[].class);
		assertTrue(marks.length == 2);
		String expectedJSON = "[{\"markId\":1,\"student\":{\"studentId\":1,\"group\":null,\"firstName\":\"Salil\",\"lastName\":\"Bansal\",\"marks\":null},\"subject\":{\"subjectId\":1,\"title\":\"Science\",\"marks\":null,\"teachers\":null},\"date\":\"2019-03-12\",\"mark\":95},{\"markId\":2,\"student\":{\"studentId\":1,\"group\":null,\"firstName\":\"Salil\",\"lastName\":\"Bansal\",\"marks\":null},\"subject\":{\"subjectId\":2,\"title\":\"Maths\",\"marks\":null,\"teachers\":null},\"date\":\"2019-03-12\",\"mark\":90}]";
		JSONAssert.assertEquals(expectedJSON, content, false);
	}

	@Test
	public void testGetMarksByStudentIdNegative() throws Exception {
		String uri = "/marks-by-student/2";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)
				.header("Authorization", accessToken)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		MarksDTO[] marks = mapFromJson(content, MarksDTO[].class);
		assertTrue(marks.length == 0);
		String expectedJSON = "[]";
		JSONAssert.assertEquals(expectedJSON, content, false);
	}

	@Test
	public void testStudentCountByTeacherIdPositive() throws Exception {
		String uri = "/student-count-by-teacher/1";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)
				.header("Authorization", accessToken)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		String expectedJSON = "{  \"studentCount\": 1,\n" + "  \"teacher\": {\n" + "    \"teachersId\": 1,\n"
				+ "    \"group\": {\n" + "      \"groupId\": 4,\n" + "      \"name\": \"Generic\",\n"
				+ "      \"students\": null,\n" + "      \"teachers\": null\n" + "    },\n" + "    \"subject\": {\n"
				+ "      \"subjectId\": 3,\n" + "      \"title\": \"Social Studies\",\n" + "      \"marks\": null,\n"
				+ "      \"teachers\": null\n" + "    }\n" + "  }\n" + "}";
		JSONAssert.assertEquals(expectedJSON, content, false);
	}

	@Test
	public void testStudentCountByTeacherIdNegative() throws Exception {
		String uri = "/student-count-by-teacher/6";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)
				.header("Authorization", accessToken)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(404, status);
		String content = mvcResult.getResponse().getContentAsString();
		String expectedJSON = "No Teacher Present with this ID";
		assertEquals(expectedJSON, content);
	}

	@Test
	public void testGetMarksPerSubjectByStudentPositive() throws Exception {
		String uri = "/marks-per-subject-by-student/1";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)
				.header("Authorization", accessToken)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		String expectedJSON = "{\n" + "  \"student\": {\n" + "    \"studentId\": 1,\n" + "    \"group\": {\n"
				+ "      \"groupId\": 2,\n" + "      \"name\": \"Non Medical\",\n" + "      \"students\": null,\n"
				+ "      \"teachers\": null\n" + "    },\n" + "    \"firstName\": \"Salil\",\n"
				+ "    \"lastName\": \"Bansal\",\n" + "    \"marks\": [\n" + "      {\n" + "        \"markId\": 2,\n"
				+ "        \"student\": {\n" + "          \"studentId\": 1,\n" + "          \"group\": null,\n"
				+ "          \"firstName\": \"Salil\",\n" + "          \"lastName\": \"Bansal\",\n"
				+ "          \"marks\": null\n" + "        },\n" + "        \"subject\": {\n"
				+ "          \"subjectId\": 2,\n" + "          \"title\": \"Maths\",\n" + "          \"marks\": null,\n"
				+ "          \"teachers\": null\n" + "        },\n" + "        \"date\": \"2019-03-12\",\n"
				+ "        \"mark\": 90\n" + "      },\n" + "      {\n" + "        \"markId\": 1,\n"
				+ "        \"student\": {\n" + "          \"studentId\": 1,\n" + "          \"group\": null,\n"
				+ "          \"firstName\": \"Salil\",\n" + "          \"lastName\": \"Bansal\",\n"
				+ "          \"marks\": null\n" + "        },\n" + "        \"subject\": {\n"
				+ "          \"subjectId\": 1,\n" + "          \"title\": \"Science\",\n"
				+ "          \"marks\": null,\n" + "          \"teachers\": null\n" + "        },\n"
				+ "        \"date\": \"2019-03-12\",\n" + "        \"mark\": 95\n" + "      }\n" + "    ]\n" + "  },\n"
				+ "  \"subjects\": [\n" + "    {\n" + "      \"subjectId\": 1,\n" + "      \"title\": \"Science\",\n"
				+ "      \"marks\": [\n" + "        {\n" + "          \"markId\": 1,\n" + "          \"student\": {\n"
				+ "            \"studentId\": 1,\n" + "            \"group\": null,\n"
				+ "            \"firstName\": \"Salil\",\n" + "            \"lastName\": \"Bansal\",\n"
				+ "            \"marks\": null\n" + "          },\n" + "          \"subject\": {\n"
				+ "            \"subjectId\": 1,\n" + "            \"title\": \"Science\",\n"
				+ "            \"marks\": null,\n" + "            \"teachers\": null\n" + "          },\n"
				+ "          \"date\": \"2019-03-12\",\n" + "          \"mark\": 95\n" + "        }\n" + "      ],\n"
				+ "      \"teachers\": [\n" + "        {\n" + "          \"teachersId\": 4,\n"
				+ "          \"group\": {\n" + "            \"groupId\": 2,\n"
				+ "            \"name\": \"Non Medical\",\n" + "            \"students\": null,\n"
				+ "            \"teachers\": null\n" + "          },\n" + "          \"subject\": {\n"
				+ "            \"subjectId\": 1,\n" + "            \"title\": \"Science\",\n"
				+ "            \"marks\": null,\n" + "            \"teachers\": null\n" + "          }\n"
				+ "        },\n" + "        {\n" + "          \"teachersId\": 2,\n" + "          \"group\": {\n"
				+ "            \"groupId\": 4,\n" + "            \"name\": \"Generic\",\n"
				+ "            \"students\": null,\n" + "            \"teachers\": null\n" + "          },\n"
				+ "          \"subject\": {\n" + "            \"subjectId\": 1,\n"
				+ "            \"title\": \"Science\",\n" + "            \"marks\": null,\n"
				+ "            \"teachers\": null\n" + "          }\n" + "        }\n" + "      ]\n" + "    },\n"
				+ "    {\n" + "      \"subjectId\": 2,\n" + "      \"title\": \"Maths\",\n" + "      \"marks\": [\n"
				+ "        {\n" + "          \"markId\": 2,\n" + "          \"student\": {\n"
				+ "            \"studentId\": 1,\n" + "            \"group\": null,\n"
				+ "            \"firstName\": \"Salil\",\n" + "            \"lastName\": \"Bansal\",\n"
				+ "            \"marks\": null\n" + "          },\n" + "          \"subject\": {\n"
				+ "            \"subjectId\": 2,\n" + "            \"title\": \"Maths\",\n"
				+ "            \"marks\": null,\n" + "            \"teachers\": null\n" + "          },\n"
				+ "          \"date\": \"2019-03-12\",\n" + "          \"mark\": 90\n" + "        }\n" + "      ],\n"
				+ "      \"teachers\": []\n" + "    }\n" + "  ]\n" + "}";
		JSONAssert.assertEquals(expectedJSON, content, false);
	}

	@Test
	public void testGetMarksPerSubjectByStudentNegative() throws Exception {
		String uri = "/marks-per-subject-by-student/6";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)
				.header("Authorization", accessToken)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(404, status);
		String content = mvcResult.getResponse().getContentAsString();
		String expectedJSON = "No Student Present with this ID";
		assertEquals(expectedJSON, content);
	}

	private String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.findAndRegisterModules();
		return objectMapper.writeValueAsString(obj);
	}

	private <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.findAndRegisterModules();
		return objectMapper.readValue(json, clazz);
	}

}
