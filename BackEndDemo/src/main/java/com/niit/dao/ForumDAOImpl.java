package com.niit.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.niit.model.Forum;
import com.niit.model.ForumComment;

@Repository
@Transactional
public class ForumDAOImpl implements ForumDAO{

	@Autowired
	private SessionFactory sessionFactory;

	
	public void saveForum(Forum forumPost) {
		
		Session session=sessionFactory.getCurrentSession();
		
		session.save(forumPost);
		
	}

	
	public List<Forum> getForums(int fapproved) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Forum where fapproved="+fapproved);
       return query.list();
	}

	
	public Forum getForumById(int fid) {
		Session session=sessionFactory.getCurrentSession();;
		Forum forumPost=(Forum)session.get(Forum.class,fid);
		return forumPost;
	}

	
	public void updateForumPost(Forum forumPost) {
		Session session=sessionFactory.getCurrentSession();
		session.update(forumPost);
		
	}

	
	public void addForumComment(ForumComment fComment) {
		Session session=sessionFactory.getCurrentSession();
		session.save(fComment);
		
	}

	
	public List getForumComments(int fid) {
		 Session session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("from ForumComment where forumPost.fid="+fid);
			return query.list();
	}

}
