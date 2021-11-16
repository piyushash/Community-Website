package com.nagarro.Backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nagarro.Backend.api.model.Role;
import com.nagarro.Backend.api.model.User;
import com.nagarro.Backend.api.repo.Rolerepo;
import com.nagarro.Backend.api.repo.UserRepository;
import com.nagarro.Backend.status.Status;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	Rolerepo rolerepo;
	
	
	public UserServiceImpl(UserRepository userRepo, Rolerepo rolerepo) {
		super();
		this.userRepo = userRepo;
		this.rolerepo = rolerepo;
	
	}

	@Override
	public Status registerUser(User newUser) {  
		if(userRepo.findByUsername(newUser.getUsername())!=null) return Status.USER_ALREADY_EXISTS;
		
        if(newUser.getPassword().equals(newUser.getConfirmpassword())) {
        	 
        	 userRepo.save(newUser);
        	 
        	 this.addRoleToUser(newUser.getUsername(), "USER");
        	 
        	 if(newUser.getUsername().equals("admin@store.com")) {
            	 this.addRoleToUser(newUser.getUsername(), "ADMIN");

        	 }
             return Status.SUCCESS;
        }
        return Status.CONFIRM_PASSWORD_MISMATCH;
	}

	@Override
	public Status loginUser(User user) {
		 User dbUser = userRepo.findByUsername(user.getUsername());
		 
		 if(dbUser==null) return Status.FAILURE;
		 if(user.getPassword().equals(dbUser.getPassword())) {
			 dbUser.setLoggedIn(true);
			 userRepo.save(dbUser);
			 return Status.SUCCESS;
		 }
			
		 return Status.FAILURE;
		 
	}

	@Override
	public Status logUserOut(User user) {
		 List<User> users = userRepo.findAll();     
	        for (User other : users) {
	            if (other.equals(user)) {
	                user.setLoggedIn(false);
	                userRepo.save(user);
	                return Status.SUCCESS;
	            }
	        }        return Status.FAILURE;
	}

	@Override
	public Status deleteUsers() {
		userRepo.deleteAll();
        return Status.SUCCESS;
	}
		

	@Override
	public Status addRoleToUser(String username, String roleName) {
		User user = userRepo.findByUsername(username);
		Role role = rolerepo.findByName(roleName);
		if(role==null) {
			role = this.saveRole(new Role(roleName));
		}
		user.getRoles().add(role);
		userRepo.save(user);
		return Status.ROLE_ADDED;
	}
	
	

	@Override
	public List<User> getUsers() {
		
		return userRepo.findAll();
	}

	@Override
	public User getUser(String username) {
		
		return userRepo.findByUsername(username);
		
	}

	@Override
	public Role saveRole(Role role) {
		return rolerepo.save(role);
	}

	


}
