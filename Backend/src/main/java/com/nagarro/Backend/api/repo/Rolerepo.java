package com.nagarro.Backend.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.Backend.api.model.Role;

public interface Rolerepo extends JpaRepository<Role,Long>{
	Role findByName(String name);
}
