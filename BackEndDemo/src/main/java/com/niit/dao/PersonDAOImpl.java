package com.niit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.model.Person;
@Transactional
@Repository
public class PersonDAOImpl implements PersonDAO {
	@Autowired
	private SessionFactory sessionFacotry;
	
	public List<Person> getAllPersons() {
	Session session=sessionFacotry.getCurrentSession();
	Query query=session.createQuery("from Person");
	List<Person> persons=query.list();
	System.out.println(persons);
		return persons;
	}

}
