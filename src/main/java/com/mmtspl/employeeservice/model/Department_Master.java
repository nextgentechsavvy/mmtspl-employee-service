package com.mmtspl.employeeservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/*
 * This is our model class and it corresponds to Department table in database
 */

@NamedQueries(  
	    {  
	        @NamedQuery(  
					        name = "@HQL_Find_Department_By_Name",  
					        query = "from Department_Master e where e.departmentName = :departmentName"  
			)  
	    }  
	)  

@Entity
@Table(name="DEPARTMENT_MASTER")
public class Department_Master {
	
	@Id
	@Column(name="departmentId")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int departmentId;
	
	@Column(name="departmentName")
	private String departmentName; 

	@Column(name="departmentLocation")
	private String departmentLocation;

	@Column(name="employeeId")
	private int employeeId;


	
	public Department_Master() {
		super();
	}
	
	public Department_Master(int departmentId, String departmentName, String departmentLocation,int employeeId) {
		super();
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.departmentLocation = departmentLocation;
		this.employeeId = employeeId;
	}
	
	
	
	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentLocation() {
		return departmentLocation;
	}

	public void setDepartmentLocation(String departmentLocation) {
		this.departmentLocation = departmentLocation;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

}
