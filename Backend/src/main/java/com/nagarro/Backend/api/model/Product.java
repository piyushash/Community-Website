package com.nagarro.Backend.api.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @NotBlank String product_name;
	private @NotBlank String brand;
	@Column(unique = true)
	private @NotBlank String code;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "productId" , referencedColumnName = "id")
	private List<Review> reviews = new ArrayList<>();
	
	public Product() {
		
	}

	public Product(Long id, @NotBlank String product_name, @NotBlank String brand, @NotBlank String code) {
		this.product_name = product_name;
		this.brand = brand;
		this.code = code;
	}

	public Long getId() {
		return id;
	}
	
	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	
	
	// methods
	
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}else if (!(obj instanceof Product)) {
			return false;
		}else {
			Product pdt = (Product) obj;
			return Objects.equals(product_name,pdt.product_name) 
					|| Objects.equals(code,pdt.code) || Objects.equals(brand,pdt.brand);
		}
		
	}
	
	@Override
	public String toString() {
		return "product{"+ "id="+ id + "productName="+ product_name + "brand = "+ brand + "code=" + code+" }";
	}
	
	
	
	

}
