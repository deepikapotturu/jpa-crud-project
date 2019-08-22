package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

	// define fields for entityManager
	private EntityManager entityManager;

	// setup constructor injection
	@Autowired
	public EmployeeDAOHibernateImpl(EntityManager thEntityManager) {
		entityManager = thEntityManager;
	}

	@Override
	public List<Employee> findAll() {
		
		//get the current hibernate session
		Session currentSession=entityManager.unwrap(Session.class);
		
		//create a query
		Query<Employee> theQuery=currentSession.createQuery("from Employee",Employee.class);
		
		//execute the query
		List<Employee> employees=theQuery.getResultList();
		
		//return the result
		return employees;
		
	}

	@Override
	public Employee findById(int theId) {
		//get current hibernate session
		Session curreSession=entityManager.unwrap(Session.class);
		
		//get the employee
		Employee theEmployee=curreSession.get(Employee.class, theId);
		
		//return the employee
		return theEmployee;
	}

	@Override
	public void save(Employee theEmployee) {
		//get the current hibernate sesion
		Session curreSession=entityManager.unwrap(Session.class);
		
		//save the employee
		curreSession.saveOrUpdate(theEmployee);
	}

	@Override
	public void deleteById(int theId) {
		
		//get the current sessionm 
		Session curreSession=entityManager.unwrap(Session.class);

		//delete the object
		Query theQuery=curreSession.createQuery("delete from Employee where id=:employeeId");
		theQuery.setParameter("employeeId", theId);
		theQuery.executeUpdate();
		
	}

}














