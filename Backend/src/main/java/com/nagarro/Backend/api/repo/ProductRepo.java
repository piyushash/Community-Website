package com.nagarro.Backend.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.Backend.api.model.Product;
import com.nagarro.Backend.api.model.Role;

public interface ProductRepo extends JpaRepository<Product,Long> {
	Product findByCode(String code);
}
