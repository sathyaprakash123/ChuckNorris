package com.example.chucknorris;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ChuckApi {

	Logger logger = LoggerFactory.getLogger(ChuckApi.class);

	@RequestMapping("/getchuckjoke")
	public String GetChuckJokes()

	{
		
		logger.info("ChuckApi Called");
		String url = "https://api.chucknorris.io/jokes/random";
		RestTemplate restTemplate = new RestTemplate();
		//ChuckPojo result = restTemplate.getForObject(url, ChuckPojo.class);
		//return result.getValue();
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		headers.add("user-agent", "Application");
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		logger.info("Headers added, about to make call");
		HttpEntity<String> entity = new HttpEntity<>(headers);
		logger.info("Calling External API");
		ResponseEntity<ChuckPojo> result = restTemplate.exchange(url, HttpMethod.GET, entity, ChuckPojo.class);
		return result.getBody().getValue();

	}

}
