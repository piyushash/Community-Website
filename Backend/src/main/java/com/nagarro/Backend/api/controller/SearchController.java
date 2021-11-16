package com.nagarro.Backend.api.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.Backend.api.model.Product;
import com.nagarro.Backend.api.model.Query;
import com.nagarro.Backend.api.repo.ProductRepo;

@CrossOrigin
@RestController
public class SearchController {
	
	@Autowired
	ProductRepo productRepo;

//	Query query;
	
	@PostMapping("/product/search")
	public HashSet<Product> productSearch(@RequestBody Query Productquery) {
		// fetching all products from database
		List<Product> allProduct = productRepo.findAll();
		
		Productquery.setSearch(Productquery.getSearch().toLowerCase());
		// result to be returned
		HashSet<Product> filtered_pdt =  new HashSet<>();
		
		
		// if all filter false
		if(!Productquery.isPdt_name() && !Productquery.isPdt_brand() && !Productquery.isPdt_code() ) {
			for(Product pdt : allProduct) {
				if(pdt.getProduct_name().toLowerCase().equals(Productquery.getSearch()) || pdt.getBrand().toLowerCase().equals(Productquery.getSearch()) 
						|| pdt.getCode().equals(Productquery.getSearch())) {
					filtered_pdt.add(pdt);
				}
					
				}
			}
		
		if(Productquery.isPdt_name()) {
			for(Product pdt : allProduct) {
				if(pdt.getProduct_name().toLowerCase().equals(Productquery.getSearch())) {
					filtered_pdt.add(pdt);
				}
			}
		}
		
		if(Productquery.isPdt_brand()) {
			for(Product pdt : allProduct) {
				if(pdt.getBrand().toLowerCase().equals(Productquery.getSearch())) {
					filtered_pdt.add(pdt);
				}
			}
		}
		
		if(Productquery.isPdt_code()) {
			for(Product pdt : allProduct) {
				if(pdt.getCode().equals(Productquery.getSearch())) {
					filtered_pdt.add(pdt);
				}
			}
		}
		
		return filtered_pdt;
	}
	
	

}
