package com.epam.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.epam.app.PasswordValidatorServiceApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PasswordValidatorServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PasswordValidatorControllerTest {

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	@Test
	public void testServiceWithInvalidPasswordWithLessLength() throws JSONException {

		com.epam.dto.Credentials credentials = new com.epam.dto.Credentials();
		credentials.setPassword("inva");

		HttpEntity<com.epam.dto.Credentials> entity = new HttpEntity<com.epam.dto.Credentials>(credentials, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/password/validate"),
				HttpMethod.POST, entity, String.class);

		JSONObject jObject = new JSONObject(response.getBody()); // json

		Assert.assertFalse("Password given is less than 5 in length and it should fail validation.",
				(Boolean) jObject.get("valid"));

	}

	@Test
	public void testServiceWithInvalidPasswordWithMoreLength() throws JSONException {

		com.epam.dto.Credentials credentials = new com.epam.dto.Credentials();
		credentials.setPassword("123456789012345678901234567890");

		HttpEntity<com.epam.dto.Credentials> entity = new HttpEntity<com.epam.dto.Credentials>(credentials, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/password/validate"),
				HttpMethod.POST, entity, String.class);

		JSONObject jObject = new JSONObject(response.getBody()); // json

		Assert.assertFalse("Password given is greater than 12 in length and it should fail validation.",
				(Boolean) jObject.get("valid"));

	}

	@Test
	public void testServiceWithInvalidPasswordWithOnlyNummbers() throws JSONException {

		com.epam.dto.Credentials credentials = new com.epam.dto.Credentials();
		credentials.setPassword("123456");

		HttpEntity<com.epam.dto.Credentials> entity = new HttpEntity<com.epam.dto.Credentials>(credentials, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/password/validate"),
				HttpMethod.POST, entity, String.class);

		JSONObject jObject = new JSONObject(response.getBody()); // json

		Assert.assertFalse("Password is all numbers with no lower case letters and it should fail validation.",
				(Boolean) jObject.get("valid"));

	}

	@Test
	public void testServiceWithInvalidPasswordWithSpecialCharacters() throws JSONException {

		com.epam.dto.Credentials credentials = new com.epam.dto.Credentials();
		credentials.setPassword("@%@#$%");

		HttpEntity<com.epam.dto.Credentials> entity = new HttpEntity<com.epam.dto.Credentials>(credentials, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/password/validate"),
				HttpMethod.POST, entity, String.class);

		JSONObject jObject = new JSONObject(response.getBody()); // json

		Assert.assertFalse("Password is having special characters and it should fail validation.",
				(Boolean) jObject.get("valid"));

	}

	@Test
	public void testServiceWithValidPasswordWithLowerCaseLettersAndNumbers() throws JSONException {

		com.epam.dto.Credentials credentials = new com.epam.dto.Credentials();
		credentials.setPassword("password123");

		HttpEntity<com.epam.dto.Credentials> entity = new HttpEntity<com.epam.dto.Credentials>(credentials, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/password/validate"),
				HttpMethod.POST, entity, String.class);

		JSONObject jObject = new JSONObject(response.getBody()); // json

		Assert.assertTrue(
				"Password is having lowercase letters and numbers and it is between 5 and 12 length in length and it should pass validation.",
				(Boolean) jObject.get("valid"));

	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}
