package com.vision.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vision.dto.UnicornDTO;
import com.vision.response.UnicornResponse;

@RestController
@RequestMapping("/api")
public class UnicornController {

	@Autowired
	@Qualifier("defaultRestTemplate")
	private RestTemplate restTemplate;
	
	@PostMapping(value = "/unicornsByEntity",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UnicornResponse> createUnicornByEntity(@RequestBody UnicornDTO unicornDto)
			throws RestClientException, JsonProcessingException {		
		return restTemplate.postForEntity("https://crudcrud.com/api/8a7742e8f57e40a281a50a80b1c97bc6/unicorns",
				unicornDto,
				UnicornResponse.class);
	}
	
	@PostMapping(value = "/unicornsByObject",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public UnicornResponse createUnicornByObject(@RequestBody UnicornDTO unicornDto)
			throws RestClientException, JsonProcessingException {		
		return restTemplate.postForObject("https://crudcrud.com/api/8a7742e8f57e40a281a50a80b1c97bc6/unicorns",
				unicornDto,
				UnicornResponse.class);
	}
	
	@GetMapping("/unicornsByEntity")
	public ResponseEntity<String> getUnicornByEntity() {		
		return restTemplate.getForEntity("https://crudcrud.com/api/8a7742e8f57e40a281a50a80b1c97bc6/unicorns",
				String.class);
	}
	
	@GetMapping("/unicornsByObject")
	public List<UnicornResponse> getUnicornByObject() {		
		return Arrays.asList(restTemplate.getForObject("https://crudcrud.com/api/8a7742e8f57e40a281a50a80b1c97bc6/unicorns",
				UnicornResponse[].class));
	}
	
	@GetMapping("/unicornsByEntity/{id}")
	public ResponseEntity<String> getUnicornByIdByEntity(@PathVariable final String id) {		
		return restTemplate.getForEntity("https://crudcrud.com/api/8a7742e8f57e40a281a50a80b1c97bc6/unicorns/" + id,
				String.class);
	}
	
	@GetMapping("/unicornsByObject/{id}")
	public UnicornResponse getUnicornByIdByObject(@PathVariable final String id) {		
		return restTemplate.getForObject("https://crudcrud.com/api/8a7742e8f57e40a281a50a80b1c97bc6/unicorns/" + id,
				UnicornResponse.class);
	}
	
	@PutMapping(value = "/unicorns/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateUnicorn(@PathVariable final String id, @RequestBody UnicornDTO unicornDto) {
		restTemplate.exchange("https://crudcrud.com/api/8bee528554df41db9966ae31ea65194a/unicorns/" + id,
				HttpMethod.PUT,
				new HttpEntity<>(unicornDto),
				Void.class);
	}
	
	@DeleteMapping("/unicorns/{id}")
	public void deleteteUnicornByIdByEntity(@PathVariable final String id) {		
		restTemplate.delete("https://crudcrud.com/api/8bee528554df41db9966ae31ea65194a/unicorns/" + id);
	}
}
