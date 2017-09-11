package com.niit.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.Friend;
import com.niit.model.User;

@Repository
@Transactional
public class FriendDAOImpl implements FriendDAO {
@Autowired
private SessionFactory sessionFactory;
	
	public List<User> getListOfSuggUser(String username) {
		Session session=sessionFactory.getCurrentSession();
		String queryString="select * from user_table1 where username in (select username from user_table1 where username!=? minus (select fromId from friend where toId=? union select toId from friend where fromId=?))";
		SQLQuery query=session.createSQLQuery(queryString);
		query.setString(0, username);
		query.setString(1, username);
		query.setString(2, username);
		query.addEntity(User.class);
		List<User> suggestedUser=query.list();
		return suggestedUser;
	}

	
	

}
