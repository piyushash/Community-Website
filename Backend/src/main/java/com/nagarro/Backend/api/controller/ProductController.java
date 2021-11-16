package com.nagarro.Backend.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.Backend.api.model.Product;
import com.nagarro.Backend.api.repo.ProductRepo;



@CrossOrigin
@RestController
public class ProductController {
	
	@Autowired
	ProductRepo productRepo;
	
	
	@PostMapping("/product/add")
	public List<Long> addProduct(@RequestBody Product data) {
		List<Long> fc = new ArrayList<>();
		if(productRepo.findByCode(data.getCode())==null) {
			productRepo.save(data);
			fc.add(1L);
			
		}else fc.add(0L);
		
		fc.add(productRepo.findByCode(data.getCode()).getId()) ;
		return fc;
		
	}
	
	@GetMapping("/product/getall")
	public List<Product> getProduct(){
		return productRepo.findAll();
	}
	
	// get product by Id
	
	@GetMapping("/product/{id}")
	public Optional<Product> getById(@PathVariable Long id) {
		if(productRepo.existsById(id)) {
			return productRepo.findById(id);
		}
		return null;
		
	}
	
	
	

}
