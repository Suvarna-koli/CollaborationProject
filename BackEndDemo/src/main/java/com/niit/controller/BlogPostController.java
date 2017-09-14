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
	public ResponseEntity<?> getBlogs(@PathVariable("approved") int approved,HttpSession session){
		if(session.getAttribute("username")==null){
			Error error=new Error(5,"UnAuthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);//401 - 2nd call back func will be executed
		}
		List<Blog> bloglist=blogPostDAO.getBlogs(approved);
		return new ResponseEntity<List<Blog>>(bloglist,HttpStatus.OK);
	}
	@RequestMapping(value="/getblogpostbyid/{blogid}",method=RequestMethod.GET)
	public ResponseEntity<?> getBlogById(@PathVariable("blogid") int blogid,HttpSession session){
		if(session.getAttribute("username")==null){
			Error error=new Error(5,"UnAuthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);//401 - 2nd call back func will be executed
		}
		Blog blogPost=blogPostDAO.getBlogById(blogid);
		return new ResponseEntity<Blog>(blogPost,HttpStatus.OK);
	}
	
	@RequestMapping(value="/updateblogpost",method=RequestMethod.PUT)
	public ResponseEntity<?> updateBlogPost(@RequestBody Blog blogPost,HttpSession session){
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
	@RequestMapping(value="/commentblog",method=RequestMethod.POST)
	public ResponseEntity<?> blogComment(@RequestBody BlogComment blogComment,HttpSession session)
	{
		if(session.getAttribute("username")==null){
			Error error=new Error(5,"Unauhorized");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		String username=(String) session.getAttribute("username");
		User user=userDAO.validateUsername(username);
		blogComment.setCommentDate(new Date());
		blogComment.setCommentedBy(user);
		try{
			blogPostDAO.addComment(blogComment);
			return new ResponseEntity<BlogComment>(blogComment,HttpStatus.OK);
		}catch(Exception e){
			Error error=new Error(1,"Unable to save the blog");
			return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@RequestMapping(value="/getcomments/{blogid}", method=RequestMethod.GET)
	public ResponseEntity<?> getblogcomments(@PathVariable("blogid") int blogid,HttpSession session)
	{
		
		if(session.getAttribute("username")==null){
			Error error=new Error(5,"Unauhorized");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		List<BlogComment> commentlist=blogPostDAO.getBlogComments(blogid);

		return new ResponseEntity<List<BlogComment>>(commentlist,HttpStatus.OK);
		
	}

}

