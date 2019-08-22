package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {

	private EntityManager entityManager;

	@Autowired
	public EmployeeDAOJpaImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@Override
	public List<Employee> findAll() {

		//create a query
		Query theQuery=entityManager.createQuery("from Employee");
		
		//execute the query
		List<Employee> employees=theQuery.getResultList();
		
		//return results
		return employees;
	}

	@Override
	public Employee findById(int theId) {
		//get employee
		Employee theEmployee=entityManager.find(Employee.class, theId);
		//return the employee
		return theEmployee;
	}

	@Override
	public void save(Employee theEmployee) {
		Employee dbEmployee=entityManager.merge(theEmployee);

		//update with id from db..so we can get generated id for save/insert
		theEmployee.setId(dbEmployee.getId());
	}

	@Override
	public void deleteById(int theId) {
	
		//delete the object
		Query theQuery=entityManager.createQuery("delete from Employee where id=:employeeId");
		theQuery.setParameter("employeeId", theId);
		theQuery.executeUpdate();
		
	}

}
