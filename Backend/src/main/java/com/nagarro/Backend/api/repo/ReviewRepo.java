package com.nagarro.Backend.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.Backend.api.model.Review;

public interface ReviewRepo extends JpaRepository<Review, Long> {

}
