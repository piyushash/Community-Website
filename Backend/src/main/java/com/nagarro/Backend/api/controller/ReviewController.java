package com.nagarro.Backend.api.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.Backend.api.model.Product;
import com.nagarro.Backend.api.model.Review;
import com.nagarro.Backend.api.repo.ProductRepo;
import com.nagarro.Backend.api.repo.ReviewRepo;
import com.nagarro.Backend.status.Status;

@CrossOrigin
@RestController
public class ReviewController {
	
	@Autowired
	ReviewRepo reviewRepo;
	@Autowired
	ProductRepo productRepo;
	
	
	@PostMapping("/review/post/{productId}")
	public Status addReview(@PathVariable(value = "productId") Long pdtId, @RequestBody Review reviewData) {
		Product currPdt = productRepo.getById(pdtId);
		if (currPdt!=null) {
			currPdt.getReviews().add(reviewData);
			productRepo.save(currPdt);
			return Status.REVIEW_ADDED;
		}
		else return Status.PRODUCT_NOT_EXISTS;
	}
	
	@GetMapping("/admin/review/get")
		public List<Review> getReview(){
			List<Review> unApprove = new ArrayList<>();
			List<Review> all = new ArrayList<>();
			all=reviewRepo.findAll();
			for(Review rev : all) {
				if(rev.isApproval()==false) {
					unApprove.add(rev);
				}
			}
			return unApprove;
			
		}
	@DeleteMapping("/admin/review/delete/{id}")
	public void deleteReviewById(@PathVariable(value="id") Long revId) {
		reviewRepo.deleteById(revId);
	}
	
	@PutMapping("/admin/review/approve")
	public void approveById(@RequestBody Long revId) {
		Review review;
		review=reviewRepo.getById(revId);
		review.setApproval(true);
		reviewRepo.save(review);
	}
}


