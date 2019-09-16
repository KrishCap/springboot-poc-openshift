package com.example.poc.client;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.example.poc.constant.SpringbootPOCConstants;
import com.example.poc.exception.SpringbootPOCException;
import com.example.poc.model.client.SampleClientResponse;
import com.example.poc.model.client.SampleUserTodosResponse;
import com.example.poc.model.client.Todos;
import com.example.poc.model.client.User;



@Service
public class ApiClient {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private Environment environment;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public SampleClientResponse retreiveResponse() throws SpringbootPOCException {
		// TODO Auto-generated method stub
		String url = environment.getProperty("sample.client.url");
		logger.info("Calling Sample client @: " + url);
		SampleClientResponse sampleClientResponse = null;
		HttpHeaders httpHeaders = new HttpHeaders();
		ResponseEntity<List<User>> response = null;
		try {
			HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);	
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<User>>(){});
			logger.info("Response from Sample client..." + response.getStatusCode() + " : " + response.getStatusCodeValue());

			if (response.getStatusCode() != HttpStatus.ACCEPTED && response.getStatusCode() != HttpStatus.OK) { // 202/200 is success
				throw new SpringbootPOCException(Integer.toString(response.getStatusCodeValue()));
			}
			sampleClientResponse = new SampleClientResponse();
			sampleClientResponse.setUsers(response.getBody());
			
		} catch (RestClientException e) {
			throw new SpringbootPOCException(SpringbootPOCConstants.SERVICE_UNAVAILABLE, null, e);
		} catch (Exception e) {
			throw new SpringbootPOCException(SpringbootPOCConstants.EXCEPTION_500, null, e);
		}

		return sampleClientResponse;

	}
	
	public SampleUserTodosResponse retreiveUserTodos() throws SpringbootPOCException {
		// TODO Auto-generated method stub
		String url = environment.getProperty("sample.todos.url");
		logger.info("Calling Sample client @: " + url);
		SampleUserTodosResponse sampleUserTodosResponse = null;
		HttpHeaders httpHeaders = new HttpHeaders();
		ResponseEntity<List<Todos>> response = null;
		try {
			HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);	
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<Todos>>(){});
			logger.info("Response from Sample User-Todos api..." + response.getStatusCode() + " : " + response.getStatusCodeValue());

			if (response.getStatusCode() != HttpStatus.ACCEPTED && response.getStatusCode() != HttpStatus.OK) { // 202/200 is success
				throw new SpringbootPOCException(Integer.toString(response.getStatusCodeValue()));
			}
			sampleUserTodosResponse = new SampleUserTodosResponse();
			sampleUserTodosResponse.setTodos(response.getBody());
			
		} catch (RestClientException e) {
			throw new SpringbootPOCException(SpringbootPOCConstants.SERVICE_UNAVAILABLE, null, e);
		} catch (Exception e) {
			throw new SpringbootPOCException(SpringbootPOCConstants.EXCEPTION_500, null, e);
		}

		return sampleUserTodosResponse;

	}

}
