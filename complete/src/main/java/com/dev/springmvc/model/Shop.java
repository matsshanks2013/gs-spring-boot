package com.dev.springmvc.model;

import hello.GoogleMapHelper;

public class Shop {
	
	public Shop(long id, String shopName, String shopNumber, String postcode, String country){
		this.id = id;
		this.shopName=shopName;
		this.shopNumber = shopNumber;
		this.postcode = postcode;
		this.country = country;
		
		
		GoogleMapHelper.populateShopLatLong(this);
		
		
	}

	public Shop() {
		// TODO Auto-generated constructor stub
	}

	private long id;
	private String shopName;
	private String shopNumber;
	private String postcode;
	private String country;
	private String lat;
	private String longitude;
	private String address;
	
	
	
	
	
	
	
	
	
	
	public String getAddress() {
		return  this.shopName + this.shopNumber + this.postcode + this.country;
	}

//	public void setAddress(String address) {
//		this.address = this.shopName + this.shopNumber + this.postcode + this.country;
//	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getShopNumber() {
		return shopNumber;
	}

	public void setShopNumber(String shopNumber) {
		this.shopNumber = shopNumber;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}


	
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	
	
	

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Shop other = (Shop) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Shop [id=" + id + ", name=" + shopName + ", shopNumber=" + shopNumber +" , postcode=" +postcode +",country="+country+"]";
	}


}
