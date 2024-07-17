package com.mmtspl.employeeservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries(  
	    {  
	        @NamedQuery(  
					        name = "@HQL_Find_Address_By_City",  
					        query = "from Address_Master a where a.city = :city"  
			)  
	    }  
	)  

@Entity
@Table(name="ADDRESS_MASTER")
public class Address_Master {
	
	@Id
	@Column(name="addressId")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int addressId;
	
	@Column(name="employeeId")
	private int employeeId;
	
	@Column(name="locality")
	private String locality;
	
	@Column(name="city")
	private String city; 
	
	@Column(name="state")
	private String state; 
	
	@Column(name="country")
	private String country; 
	
	@Column(name="zipcode")
	private int zipcode; 
	
	@Column(name="addressType")
	private String addressType;
	
	
	public Address_Master() {
		super();
	}
	
	public Address_Master(int addressId, int employeeId, String locality, String city, String state, String country, int zipcode, String addressType) {
		this.addressId = addressId;
		this.employeeId = employeeId;
		this.locality = locality;
		this.city = city; 
		this.state = state; 
		this.country = country; 
		this.zipcode = zipcode; 
		this.addressType = addressType;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}
	
	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

}
