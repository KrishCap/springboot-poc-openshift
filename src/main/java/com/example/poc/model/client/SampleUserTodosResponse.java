package com.example.poc.model.client;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SampleUserTodosResponse {
	
	@JsonProperty("todos")
	  private List<Todos> todos = new ArrayList<Todos>();

	public List<Todos> getTodos() {
		return todos;
	}

	public void setTodos(List<Todos> todos) {
		this.todos = todos;
	}
	
	

}
