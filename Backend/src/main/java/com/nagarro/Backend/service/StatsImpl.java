package com.nagarro.Backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.Backend.api.repo.ProductRepo;
import com.nagarro.Backend.api.repo.ReviewRepo;
import com.nagarro.Backend.api.repo.UserRepository;

@Service
public class StatsImpl implements StatsService{

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	ProductRepo productRepo;
	
	@Autowired
	ReviewRepo reviewRepo;
	
	@Override
	public Long countUser() {
		
		return userRepo.count();
	}

	@Override
	public Long countProduct() {
		
		return productRepo.count();
	}

	@Override
	public Long countReview() {
		
		return reviewRepo.count();
	}

}
