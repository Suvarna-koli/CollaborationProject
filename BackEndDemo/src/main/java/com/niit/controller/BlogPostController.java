package com.niit.controller;

import java.util.Date;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.BlogPostDAO;
import com.niit.dao.UserDAO;
import com.niit.model.Blog;
import com.niit.model.Error;
import com.niit.model.*;
@RestController
public class BlogPostController {
	@Autowired
	private BlogPostDAO blogPostDAO;
	@Autowired
	private UserDAO userDAO;
	
	
	
	@RequestMapping(value="/saveBlog",method=RequestMethod.POST)
	public ResponseEntity<?> saveBlog(@RequestBody Blog blogPost,HttpSession session){
		if (session.getAttribute("username") == null) {
            Error error = new Error(3, "Unauthorized user");
			return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);
		}	
		
			String username=(String)session.getAttribute("username");
			
			User user=userDAO.validateUsername(username);
			System.out.println(user.getUsername());
			blogPost.setPostedOn(new Date());
			blogPost.setCreatedBy(user);
			System.out.println("in back conytrl"+blogPost.getBlogtitle());
			try{
			blogPostDAO.saveBlog(blogPost);
			return new ResponseEntity<Blog>(blogPost,HttpStatus.OK);
			}
		catch(Exception e){
			Error error=new Error(2,"Cannot insert blog post details");
			return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
				
	}
	@RequestMapping(value="/getblogposts/{approved}",method=RequestMethod.GET)
	public ResponseEntity<?> getBlogPosts(@PathVariable("approved") int approved,HttpSession session){
		if(session.getAttribute("username")==null){
			Error error=new Error(5,"UnAuthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);//401 - 2nd call back func will be executed
		}
		List<Blog> bloglist=blogPostDAO.getblogs(approved);
		return new ResponseEntity<List<Blog>>(bloglist,HttpStatus.OK);
	}
	@RequestMapping(value="/getblogpostbyid/{blogid}",method=RequestMethod.GET)
	public ResponseEntity<?> getBlogPostById(@PathVariable("blogid") int blogid,HttpSession session){
		if(session.getAttribute("username")==null){
			Error error=new Error(5,"UnAuthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);//401 - 2nd call back func will be executed
		}
		Blog blogPost=blogPostDAO.getBlogById(blogid);
		return new ResponseEntity<Blog>(blogPost,HttpStatus.OK);
	}
	
	@RequestMapping(value="/updateblogpost",method=RequestMethod.PUT)
	public ResponseEntity<?> UpdateBlogPost(@RequestBody Blog blogPost,HttpSession session){
		if (session.getAttribute("username") == null) {
            Error error = new Error(5, "Unauthorized user");
			return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);
		}	
			try{
			blogPostDAO.updateBlogPost(blogPost);
			return new ResponseEntity<Void>(HttpStatus.OK);
			}
		catch(Exception e){
			Error error=new Error(2,"Cannot insert blog post details");
			return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
