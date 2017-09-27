package com.niit.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.model.User;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void registerUser(User user) {
		
		Session session=sessionFactory.getCurrentSession();
		System.out.println(user.getFirstname());
		session.save(user);
	}


	public User validateUsername(String username) {
		Session session=sessionFactory.getCurrentSession();
		User user=(User)session.get(User.class, username);
		System.out.println(user.getUsername());
		return user;
	}

	public User validateEmail(String email) {
Session session=sessionFactory.getCurrentSession();
Query query=session.createQuery("from User where email=?");
User user=(User) query.list();
return user;
	}


	public User login(User user) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from User where username=? and password=?");
		query.setString(0, user.getUsername());
		query.setString(1, user.getPassword());
	return (User)query.uniqueResult();
	}



	public void updateUser(User user) {
		Session session=sessionFactory.getCurrentSession();
		session.update(user);
		
	}

	public User getUserByUsername(String username){
		Session session=sessionFactory.getCurrentSession();
		
		User user=(User)session.get(User.class,username);
		
		return user;
	}
}
