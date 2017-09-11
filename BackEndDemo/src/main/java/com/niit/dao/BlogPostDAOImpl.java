package com.niit.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.Blog;

@Repository
@Transactional
public class BlogPostDAOImpl implements BlogPostDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public void saveBlog(Blog blogPost) {
		System.out.println("saving blog from dao impll");
	Session session=sessionFactory.getCurrentSession();
	System.out.println(blogPost.getBlogtitle());
	session.save(blogPost);
	

	}
	
	public List<Blog> getblogs(int approved) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Blog where approved="+approved);
       return query.list();
	}

	public Blog getBlogById(int blogid) {
		Session session=sessionFactory.getCurrentSession();;
		Blog blogPost=(Blog)session.get(Blog.class,blogid);
		return blogPost;
	}


	public void updateBlogPost(Blog blogPost) {
		Session session=sessionFactory.getCurrentSession();
		session.update(blogPost);
		
	}



/*	public void addComment(BlogComment blogComment) {
		Session session=sessionFactory.openSession();
		session.save(blogComment);
		session.flush();
		session.close();
		
	}

	public List<BlogComment> getBlogComments(int blogId) {
	 Session session=sessionFactory.openSession();
	Query query=session.createQuery("from BlogComment where blogPost.id="+blogId);
	List<BlogComment> blogComments=query.list();
	System.out.println(blogComments);
	session.close();
	return blogComments;
	}*/

}
