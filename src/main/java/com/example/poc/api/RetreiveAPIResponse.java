package com.example.poc.api;

import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonProperty;

public class RetreiveAPIResponse {

		
		  @JsonProperty("users")
		  private List<User> users = new ArrayList<User>();

		public List<User> getUsers() {
			return users;
		}

		public void setUsers(List<User> users) {
			this.users = users;
		}

}
