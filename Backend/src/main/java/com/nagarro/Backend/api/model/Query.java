package com.nagarro.Backend.api.model;

public class Query {
	
	String search;
	boolean pdt_name = false;
	boolean pdt_code = false;
	boolean pdt_brand = false;
	
	public Query() {
		
	}
	
	
	
	public Query(String search, boolean pdt_name, boolean pdt_code, boolean pdt_brand) {
		this.search = search;
		this.pdt_name = pdt_name;
		this.pdt_code = pdt_code;
		this.pdt_brand = pdt_brand;
	}



	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public boolean isPdt_name() {
		return pdt_name;
	}
	public void setPdt_name(boolean pdt_name) {
		this.pdt_name = pdt_name;
	}
	public boolean isPdt_code() {
		return pdt_code;
	}
	public void setPdt_code(boolean pdt_code) {
		this.pdt_code = pdt_code;
	}
	public boolean isPdt_brand() {
		return pdt_brand;
	}
	public void setPdt_brand(boolean pdt_brand) {
		this.pdt_brand = pdt_brand;
	}
	
	
}
