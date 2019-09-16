package com.example.poc.handler;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.poc.client.ApiClient;
import com.example.poc.exception.SpringbootPOCException;
import com.example.poc.model.client.Todos;
import com.example.poc.model.client.User;


@Service
public class POCAsyncHandler {
	
	 private Logger logger = LoggerFactory.getLogger(this.getClass());
	 
	 @Autowired
		private ApiClient apiClient;
	
	@Async("asyncExecutor")
	public CompletableFuture<List<User>> retrieveUsers() throws SpringbootPOCException {
		logger.debug("retrieveUsers completed");
		List<User> users = apiClient.retreiveResponse().getUsers();
		logger.debug("retrieveUsers completed");
		return CompletableFuture.completedFuture(users);
	}

	
	@Async("asyncExecutor")
	public CompletableFuture<List<Todos>> retrieveUsersTodos() throws SpringbootPOCException {
		logger.debug("retrieveTodos started");
		List<com.example.poc.model.client.Todos> todos = apiClient.retreiveUserTodos().getTodos();
		logger.debug("retrieveTodos completed");
		return CompletableFuture.completedFuture(todos);
	}
}
