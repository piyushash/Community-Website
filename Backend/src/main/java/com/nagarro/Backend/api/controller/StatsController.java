package com.nagarro.Backend.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.Backend.service.StatsService;

@CrossOrigin
@RestController
public class StatsController {
	
	@Autowired
	StatsService statsService;
	
	@GetMapping("/stats")
	public List<Long> getStats(){
		List<Long> stats = new ArrayList<>(); 
		
		stats.add(statsService.countUser());
		stats.add(statsService.countProduct());
		stats.add(statsService.countReview());
		
		return stats;
	}
	
	

}
