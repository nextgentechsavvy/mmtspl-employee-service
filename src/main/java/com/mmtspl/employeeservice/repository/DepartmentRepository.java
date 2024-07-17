package com.mmtspl.employeeservice.repository;

import java.util.List;
import java.util.stream.Collectors;

import com.mmtspl.employeeservice.exception.DepartmentInfoByIDNotFoundException;
import com.mmtspl.employeeservice.exception.DepartmentInfoByNameNotFoundException;
import com.mmtspl.employeeservice.exception.NoDepartmentDataFoundException;
import com.mmtspl.employeeservice.model.Department_Master;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DepartmentRepository {

	@Autowired
	SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	@SuppressWarnings("unchecked")
	public List<Department_Master> getAllDepartments() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Department_Master> departmentList = null;
		try {
			departmentList = session.createQuery("from Department_Master").list();
		}catch(NoDepartmentDataFoundException ndf) {
			ndf.printStackTrace();
		}finally {
			//session.close();
		}
		return departmentList;
	}

	public Department_Master getDepartmentByID(int departmentId) {
		Session session = this.sessionFactory.getCurrentSession();
		Department_Master department = null;
		try {
			department = session.get(Department_Master.class, departmentId);
		}catch(DepartmentInfoByIDNotFoundException dIBIDnf) {
			dIBIDnf.printStackTrace();
		}finally {
			//session.close();
		}
		
		return department;
	}
	
	// ****************** @NamedQueries ********************** //
	@SuppressWarnings("unchecked")
	public List<Department_Master> getDepartmentByName(String departmentName) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = null;
		//Optional<Employee> employeeOptional = null;
		List<Department_Master> departmentList= null;
		
		try {
				query = session.getNamedQuery("@HQL_Find_Department_By_Name");
				query.setParameter("departmentName", departmentName);
				System.out.println("Named Query is : " + query.getQueryString());
				
				departmentList = (List<Department_Master>) query.list().stream().collect(Collectors.toList());
				//employeeList = session.createQuery(query.toString()).list();
				
				if(departmentList.isEmpty())
					departmentList = null;
		}catch(DepartmentInfoByNameNotFoundException enfbn) {
			enfbn.printStackTrace();
		}finally {
			//session.close();
		}
		
		return departmentList;
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
