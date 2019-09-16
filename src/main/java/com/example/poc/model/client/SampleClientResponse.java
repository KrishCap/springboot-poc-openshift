package com.example.poc.model.client;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SampleClientResponse {
	
	  @JsonProperty("users")
	  private List<User> users = new ArrayList<User>();

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	  
	  

}
