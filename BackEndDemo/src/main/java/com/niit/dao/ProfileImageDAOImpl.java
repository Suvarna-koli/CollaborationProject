package com.niit.dao;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.ProfileImage;

@Repository
@Transactional
public class ProfileImageDAOImpl implements ProfileImageDAO{


	@Autowired
	SessionFactory sessionFactory;

	public void uploadProfilePic(ProfileImage profileImage) {
		Session session=sessionFactory.openSession();
		session.saveOrUpdate(profileImage);
		session.flush();
		session.close();
		
	}

	
	public ProfileImage getProfilePic(String username) 
	{
		Session session=sessionFactory.openSession();
		ProfileImage profileImage=(ProfileImage) session.get(ProfileImage.class,username);
		session.close();
		return profileImage;
		
	}


	
}
