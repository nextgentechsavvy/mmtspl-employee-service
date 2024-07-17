package com.mmtspl.employeeservice.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mmtspl.employeeservice.exception.AddressInfoByCityNotFoundException;
import com.mmtspl.employeeservice.exception.AddressInfoByIDNotFoundException;
import com.mmtspl.employeeservice.exception.NoAddressDataFoundException;
import com.mmtspl.employeeservice.model.Address_Master;

@Repository
public class AddressRepository {
	
	@Autowired
	SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	@SuppressWarnings("unchecked")
	public List<Address_Master> getAllAddress() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Address_Master> addressList = null;
		try {
			addressList = session.createQuery("from Address_Master").list();
		}catch(NoAddressDataFoundException naf) {
			naf.printStackTrace();
		}finally {
			//session.close();
		}
		return addressList;
	}

	public Address_Master getAddressByID(int addressId) {
		Session session = this.sessionFactory.getCurrentSession();
		Address_Master address = null;
		try {
			address = session.get(Address_Master.class, addressId);
		}catch(AddressInfoByIDNotFoundException aibIDnf) {
			aibIDnf.printStackTrace();
		}finally {
			//session.close();
		}
		
		return address;
	}
	
	// ****************** @NamedQueries ********************** //
	@SuppressWarnings("unchecked")
	public List<Address_Master> getAddressByCity(String city) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = null;
		//Optional<Employee> employeeOptional = null;
		List<Address_Master> addressList= null;
		
		try {
				query = session.getNamedQuery("@HQL_Find_Address_By_City");
				query.setParameter("city", city);
				System.out.println("Named Query is : " + query.getQueryString());
				
				addressList = (List<Address_Master>) query.list().stream().collect(Collectors.toList());
				//employeeList = session.createQuery(query.toString()).list();
				
				if(addressList.isEmpty())
					addressList = null;
		}catch(AddressInfoByCityNotFoundException aibcnfe) {
			aibcnfe.printStackTrace();
		}finally {
			//session.close();
		}
		
		return addressList;
	}
	
	
	/*
	public Employee_Master getEmployee(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Employee_Master employee = null;
		try {
			employee = session.get(Employee_Master.class, id);
		}catch(EmployeeNotFoundException enf) {
			enf.printStackTrace();
		}finally {
			
		}
		
		return employee;
	}


	public List<Employee_Master> getEmployeeByName(String employeeName) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = null;
		//Optional<Employee> employeeOptional = null;
		List<Employee_Master> employeeList= null;
		
		try {
				query = session.getNamedQuery("@HQL_Find_Employee_By_Name");
				query.setParameter("employeeName", employeeName);
				System.out.println("Named Query is : " + query.getQueryString());
				
				employeeList = (List<Employee_Master>) query.list().stream().collect(Collectors.toList());
				//employeeList = session.createQuery(query.toString()).list();
				
				if(employeeList.isEmpty())
					employeeList = null;
		}catch(EmployeeNotFoundByNameException enfbn) {
			enfbn.printStackTrace();
		}finally {
			
		}
		
		return employeeList;
	}
	
	public Employee_Master addEmployee(Employee_Master employee) {
		Session session = this.sessionFactory.getCurrentSession();
		Serializable iStatus = session.save(employee);
		return employee;
	}

	public void updateEmployee(Employee_Master employee) {
		Session session = this.sessionFactory.getCurrentSession();
		Hibernate.initialize(employee);
		session.update(employee);
	}

	public String deleteEmployee(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Employee_Master p = (Employee_Master) session.get(Employee_Master.class, id);
		System.out.println(p.getEmployeeId());
		if (null != p) {
			session.delete(p);
		}
		return "Deleted Employee Record is:" + " \n\n" + "Employee ID: " + p.getEmployeeId() + "  Employee Name: " + p.getEmployeeName() + "  Employee Salary: " + p.getEmployeeSalary() + "  Employee Age: " + p.getEmployeeAge() + "  Employee Designation: " + p.getEmployeeDesignation() ;
	}
*/
}
