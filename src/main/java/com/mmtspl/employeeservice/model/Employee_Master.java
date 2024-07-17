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
 * This is our model class and it corresponds to Employee table in database
 */

@NamedQueries(  
	    {  
	        @NamedQuery(  
					        name = "@HQL_Find_Employee_By_Name",  
					        query = "from Employee_Master e where e.employeeName = :employeeName"  
			),  
	        @NamedQuery(  
			        name = "@HQL_Find_Emp_Dept_Details_By_employeeId",  
			        query = "SELECT e.employeeId, e.employeeName, e.employeeSalary, e.employeeAge, e.employeeDesignation,e.employeeAddress, "
			        		+ " d.departmentName, d.departmentLocation, "
			        		+ "a.locality, a.city, a.state, a.country, a.zipcode "
			        		+ "FROM Employee_Master e, Department_Master d, Address_Master a "
			        		+ "WHERE e.employeeId = d.employeeId "
			        		+ "AND e.employeeId = a.employeeId "
			        		+ "AND e.employeeId = :employeeId"  
	        )
				/*
				 * ,
				 * 
				 * @NamedQuery( name = "@HQL_Find_Emp_Dept_Details_By_Join", query =
				 * "SELECT e.employeeId, e.employeeName, d.departmentName " +
				 * "FROM Employee_Master e INNER JOIN Department_Master d " +
				 * "ON e.employeeId = d.employeeId " + "AND e.employeeId = :employeeId" )
				 */  
	    }  
	)  
//select e.employeeId, e.employeeName,e.employeeSalary, e.employeeAge, e.employeeDesignation,e.employeeAddress,d.departmentName,d.departmentLocation from employee_master e, department_master d where e.employeeId = d.employeeId;
@Entity
@Table(name="EMPLOYEE_MASTER")
public class Employee_Master {
	
	@Id
	@Column(name="employeeId")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int employeeId;
	
	@Column(name="employeeName")
	String employeeName; 

	@Column(name="employeeSalary")
	String employeeSalary;

	@Column(name="employeeAge")
	int employeeAge;

	@Column(name="employeeDesignation")
	String employeeDesignation;

	@Column(name="employeeAddress")
	String employeeAddress;
	
	
	
	public Employee_Master() {
		super();
	}
	
	public Employee_Master(int employeeId, String employeeName, String employeeSalary, int employeeAge, String employeeDesignation, String employeeAddress) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employeeSalary = employeeSalary;
		this.employeeAge = employeeAge;
		this.employeeDesignation = employeeDesignation;
		this.employeeAddress = employeeAddress;
	}
	
	
	
	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeSalary() {
		return employeeSalary;
	}

	public void setEmployeeSalary(String employeeSalary) {
		this.employeeSalary = employeeSalary;
	}
	
	public int getEmployeeAge() {
		return employeeAge;
	}

	public void setEmployeeAge(int employeeAge) {
		this.employeeAge = employeeAge;
	}

	public String getEmployeeDesignation() {
		return employeeDesignation;
	}

	public void setEmployeeDesignation(String employeeDesignation) {
		this.employeeDesignation = employeeDesignation;
	}

	public String getEmployeeAddress() {
		return employeeAddress;
	}

	public void setEmployeeAddress(String employeeAddress) {
		this.employeeAddress = employeeAddress;
	}

	

}
