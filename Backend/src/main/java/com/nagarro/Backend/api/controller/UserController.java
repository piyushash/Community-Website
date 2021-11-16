package com.nagarro.Backend.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.nagarro.Backend.api.model.Role;
import com.nagarro.Backend.api.model.User;
import com.nagarro.Backend.service.UserService;
import com.nagarro.Backend.status.Status;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@CrossOrigin
@RestController
public class UserController {
    
    @Autowired
    UserService userService;
     
    
    public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
    
	@PostMapping("/users/register")
    public Status registerUser(@Validated @RequestBody User newUser) {
       return userService.registerUser(newUser);
    }   
	
    @PostMapping("/users/login")
    public User login(@Validated @RequestBody User user, HttpServletRequest request) {

    	HttpSession session = request.getSession();

    	if(userService.loginUser(user)==Status.SUCCESS) {
    		
	    	User currUser = userService.getUser(user.getUsername());
	    	session.setAttribute("token", currUser);
	    	System.out.println("login "+session.getAttribute("token")); // debug
	    	return currUser;
    	}
    	return null;
    	
    }   
    
    
    @GetMapping("/users/logout")
    public Status logUserOut(HttpServletRequest request) {
    	HttpSession session = request.getSession();
    	User user = (User) session.getAttribute("token");
    	System.out.println("logout "+user); // debug
    	session.removeAttribute("token");
    	session.invalidate();
    	return userService.logUserOut(user);
    }   
    
    @DeleteMapping("/users/deleteAll")
    public Status deleteUsers() {
    	return userService.deleteUsers();
    }
    
    // returns all user 
    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
    	return ResponseEntity.ok().body(userService.getUsers());
    }
    
    // Saves Role
    @PostMapping("/role/save")
    public ResponseEntity<Role>saveRole(@RequestBody Role role){
    	return ResponseEntity.ok().body(userService.saveRole(role));
    }
    
    @PostMapping("/role/adduser")
    public ResponseEntity<?>addRoleToUser(@RequestBody RoleToUserForm form){
    	return ResponseEntity.ok().body(userService.addRoleToUser(form.getUsername(), form.getRolename()));
    }
    
    @GetMapping("/users/token")
    public User getToken(HttpServletRequest request) {
    	HttpSession session = request.getSession();
    	System.out.println("token "+session.getAttribute("token")); // debug
    	return (User) session.getAttribute("token");
    }
    
    
    
}

class RoleToUserForm{
	private String username;
	private String rolename;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	
	
}