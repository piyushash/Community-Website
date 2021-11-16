package com.nagarro.Backend.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "review")
public class Review {
	
	@Id
	@GeneratedValue
	private Long id;
	@NotBlank
	
	@Column(columnDefinition = "TEXT")
	private String review;
	
	private Long productId;
	@NotBlank
	private int rating;
	@NotBlank
	private String heading;
	
	private boolean approval; 
	
	public Review() {
		
	}

	public Review(@NotBlank String review,@NotBlank int rating ,@NotBlank String heading) {
		this.review = review;
		this.heading = heading;
		this.rating = rating;
		this.approval = false;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	


	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}
	
	
	
	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}
	
	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}
	
	

	public boolean isApproval() {
		return approval;
	}

	public void setApproval(boolean approval) {
		this.approval = approval;
	}

	@Override
    public String toString() {
        return "review{" +
                "id=" + id +
                ", review='" + review + 
                '}';
    }
	
	

}
