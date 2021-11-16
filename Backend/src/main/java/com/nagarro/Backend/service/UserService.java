package com.nagarro.Backend.service;

import java.util.List;

import com.nagarro.Backend.api.model.Role;
import com.nagarro.Backend.api.model.User;
import com.nagarro.Backend.status.Status;

public interface UserService {

		Role saveRole(Role role);
		
		public Status registerUser( User newUser);
		
		public Status loginUser( User user);
		
		public Status logUserOut( User user);
		
		public Status deleteUsers();
		
		public User getUser(String username);
		
		Status addRoleToUser(String username , String roleName);
		 
		List<User>getUsers();
		
}
