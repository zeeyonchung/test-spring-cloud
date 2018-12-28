package com.example.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;

@Controller
@SpringBootApplication
public class ClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}

	@RequestMapping("/member-index")
	public String member() {
		return "member";
	}

	@RequestMapping("/login")
	public @ResponseBody String login(HttpServletRequest req) throws IOException {
		ResponseEntity<String> response = null;

		RestTemplate restTemplate = new RestTemplate();

		String credentials = "foo:bar";
		String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("Authorization", "Basic " + encodedCredentials);

		HttpEntity<String> entity = new HttpEntity<String>(headers);

		String access_token_url = "http://localhost:8080/auth/oauth/token";
		access_token_url += "?username=testuser&password=testpwd";
		access_token_url += "&grant_type=password&scope=read";

		response = restTemplate.exchange(access_token_url, HttpMethod.POST, entity, String.class);

		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(response.getBody());
		String token = node.path("access_token").asText();

		req.getSession().setAttribute("token", token);

//		return response.getBody();
		return token;
	}

	@RequestMapping("/member")
	public @ResponseBody String member(HttpServletRequest req) {
		String token = (String)req.getSession().getAttribute("token");

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + token);
		HttpEntity<String> entity = new HttpEntity<>(headers);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/a1/member", HttpMethod.GET, entity, String.class);

		return response.getBody();
	}
}

