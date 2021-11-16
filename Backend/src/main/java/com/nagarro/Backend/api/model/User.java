package com.nagarro.Backend.api.model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;



import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
    private @Id @GeneratedValue long id;
    private  @NotBlank String username;
    private  @NotBlank String password;
    private  @NotBlank String confirmpassword;
    
    @ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private Collection<Role> roles= new ArrayList<>();
    
    private  String firstname;
    private  String lastname;
    private  @NotBlank boolean loggedIn; 
    
    public User() {
    }
    
    public User(@NotBlank String username, 
    			@NotBlank  String password,String firstname,
    			String lastname, @NotBlank String confirmpassword) {
        this.username = username;
        this.password = password;
        this.loggedIn = false;
        this.firstname = firstname;
        this.lastname =  lastname;
        this.confirmpassword = confirmpassword;
        
    }   
    
    // Getters & Setters
    
    public String getFirstname() {
		return firstname;
	}
    

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	  
    public long getId() {
        return id;
    }   
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public boolean isLoggedIn() {
        return loggedIn;
    }
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
    
    public String getConfirmpassword() {
    	return confirmpassword;
    }
    public void setConfirmpassword(String confirmpassword) {
    	this.confirmpassword = confirmpassword;
    }
    
    
    
    public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	// methods
    @Override
    public boolean equals(Object o) {
        if (this == o) 
        	return true;
        if (!(o instanceof User)) 
        	return false;
        User user = (User) o;
        return Objects.equals(username, user.username) &&
                Objects.equals(password, user.password);
    }   
    
    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, 
                            loggedIn);
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", loggedIn=" + loggedIn + '\''+
                ", name= " + firstname +' '+ lastname+ 
                '}';
    }
}