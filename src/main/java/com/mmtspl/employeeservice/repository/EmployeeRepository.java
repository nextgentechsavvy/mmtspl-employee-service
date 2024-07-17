package com.mmtspl.employeeservice.repository;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;

import com.mmtspl.employeeservice.exception.EmployeeInfoByIDNotFoundException;
import com.mmtspl.employeeservice.exception.EmployeeInfoByNameNotFoundException;
import com.mmtspl.employeeservice.exception.NoEmployeeDataFoundException;
import com.mmtspl.employeeservice.exception.RecordNotFoundNullPointerException;
import com.mmtspl.employeeservice.model.EmployeeAddress;
import com.mmtspl.employeeservice.model.Employee_Master;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepository {

	@Autowired
	SessionFactory sessionFactory;
	
	private String message ="";
	private Boolean matched = false;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	// ****************** Calling from FrontController ********************** //

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Employee_Master> getAllEmployee() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Employee_Master> employeeList = null;
		try {
			employeeList = session.createQuery("from Employee_Master order by employeeName").list();
		}catch(NoEmployeeDataFoundException ndf) {
			ndf.printStackTrace();
		}catch(RecordNotFoundNullPointerException rnfnpe) {
			rnfnpe.printStackTrace();
		}finally {
			
		}
		return employeeList;
	}

	@Transactional
	public Employee_Master getEmployeeById(int employeeId) {
		Session session = this.sessionFactory.getCurrentSession();
		Employee_Master employee = null;
		try {
			employee = session.get(Employee_Master.class, employeeId);
		}catch(EmployeeInfoByIDNotFoundException enf) {
			enf.printStackTrace();
		}catch(RecordNotFoundNullPointerException rnfnpe) {
			rnfnpe.printStackTrace();
		}finally {
			
		}
		
		return employee;
	}

	@Transactional
	public Employee_Master addEmployee(Employee_Master employee) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
		Serializable savedEmployeeId = session.save(employee);
		if(savedEmployeeId != null)
			System.out.println("Saved Employee ID :: " + savedEmployeeId);
		else
			System.out.println("Employee Record Not Saved... ");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return employee;
	}

	@Transactional
	public void updateEmployee(Employee_Master employee) {
		Session session = this.sessionFactory.getCurrentSession();
		Hibernate.initialize(employee);
		session.update(employee);
	}

	@Transactional
	public void updateEmployeeById(Employee_Master employee, int employeeId) {
		Session session = this.sessionFactory.getCurrentSession();
		Hibernate.initialize(employee);
		session.saveOrUpdate(String.valueOf(employeeId), employee);
	}
	
	@Transactional
	public Boolean deleteEmployee(int employeeId) {
		System.out.println("deleteEmployee :: " + employeeId);
		Session session = this.sessionFactory.getCurrentSession();
		//Transaction transaction = session.beginTransaction();
		Employee_Master employee_master = (Employee_Master) session.get(Employee_Master.class, employeeId);
		
		try {
			if (employee_master != null) {
				matched = true;
				System.out.println(employee_master.getEmployeeId());
				session.delete(employee_master);
				message = "Deleted Employee Record is:" + " \n" + "Employee ID: " + employee_master.getEmployeeId() + "  Employee Name: " + employee_master.getEmployeeName() + "  Employee Salary: " + employee_master.getEmployeeSalary() + "  Employee Age: " + employee_master.getEmployeeAge() + "  Employee Designation: " + employee_master.getEmployeeDesignation() + "\n"; 
			}else{
				matched = false;
				message = "Employee with ID : " + employeeId + " not found....";
			}
		}catch(EmployeeInfoByIDNotFoundException enf) {
			enf.printStackTrace();
		}finally {
			//transaction.commit();
		}
		
		System.out.println("Delete message :: " + message);
		return matched;
	}
	
	// ****************** @NamedQueries ********************** //
		@SuppressWarnings("unchecked")
		@Transactional
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
			}catch(EmployeeInfoByNameNotFoundException enfbn) {
				enfbn.printStackTrace();
			}finally {
				
			}
			
			return employeeList;
		}

		// ****************** Calling from FrontController ********************** //

	




		
		

	@Transactional
	public void updateEmployeeByAddress(EmployeeAddress employeeAddress, int employeeId) {
		Session session = this.sessionFactory.getCurrentSession();
		Hibernate.initialize(employeeAddress);
		session.saveOrUpdate(String.valueOf(employeeId), employeeAddress);
	}
	
	// ****************** @NamedQueries ********************** //
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Object[]> getEmpDeptDetailsByEmpId(int employeeId) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = null;
		//Optional<Employee> employeeOptional = null;
		List<Object[]> employeeList= null;
		
		try {
				query = session.getNamedQuery("@HQL_Find_Emp_Dept_Details_By_employeeId");
				query.setParameter("employeeId", employeeId);
				System.out.println("Named Query is : " + query.getQueryString());
				
				employeeList = (List<Object[]>) query.list().stream().collect(Collectors.toList());
				//employeeList = session.createQuery(query.toString()).list();
				
				if(employeeList.isEmpty())
					employeeList = null;
				
				
				/*
				 * EntityManagerFactory emf; EntityManager em = emf.createEntityManager();
				 * em.getTransaction().begin();
				 * 
				 * List<Object[]> results = em.
				 * createQuery("SELECT p.firstName, p.lastName, n.phoneNumber FROM Person p JOIN PhoneBookEntry n ON p.firstName = n.firstName AND p.lastName = n.lastName"
				 * ).getResultList();
				 * 
				 * for (Object[] result : results) { System.out.println(result[0] + " " +
				 * result[1] + " - " + result[2]); }
				 * 
				 * em.getTransaction().commit(); em.close();
				 */
				
		}catch(EmployeeInfoByNameNotFoundException enfbn) {
			enfbn.printStackTrace();
		}finally {
			
		}
		
		return employeeList;
	}
	

	
	
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Employee_Master> getDepartmentDetailsByEmployeeID(int employeeId) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Employee_Master> employeeList= null;
		//Employee_Master employee = null;
		try {
			//employee = session.get(Employee_Master.class, employeeId);
			
			Criteria criteria = session.createCriteria(Employee_Master.class);  
			criteria.add(Restrictions.gt("salary",10000));//salary is the propertyname  
			employeeList = criteria.list();  
			
			
		}catch(EmployeeInfoByIDNotFoundException enf) {
			enf.printStackTrace();
		}finally {
			
		}
		
		return employeeList;
	}
	
	
	
	
	
	
	

}
