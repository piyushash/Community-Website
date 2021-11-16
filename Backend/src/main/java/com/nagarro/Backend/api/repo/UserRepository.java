package com.nagarro.Backend.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.Backend.api.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}