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

import com.niit.dao.ForumDAO;
import com.niit.dao.UserDAO;
import com.niit.model.Error;
import com.niit.model.Forum;
import com.niit.model.ForumComment;
import com.niit.model.User;

@RestController
public class ForumController {
	@Autowired
	private ForumDAO forumDAO;
	@Autowired
	private UserDAO userDAO;
	
	
	
	@RequestMapping(value="/saveForum",method=RequestMethod.POST)
	public ResponseEntity<?> saveForum(@RequestBody Forum forumPost,HttpSession session){
		if (session.getAttribute("username") == null) {
            Error error = new Error(3, "Unauthorized user");
			return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);
		}	
		
			String username=(String)session.getAttribute("username");
			
			User user=userDAO.validateUsername(username);
			System.out.println(user.getUsername());
			forumPost.setFpostedOn(new Date());
			forumPost.setFcreatedBy(user);

			System.out.println("in back conytrl"+forumPost.getFtitle());
			try{
			forumDAO.saveForum(forumPost);
			return new ResponseEntity<Forum>(forumPost,HttpStatus.OK);
			}
		catch(Exception e){
			Error error=new Error(2,"Cannot insert blog post details");
			return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
				
	}
	@RequestMapping(value="/getforum/{fapproved}",method=RequestMethod.GET)
	public ResponseEntity<?> getForums(@PathVariable("fapproved") int approved,HttpSession session){
		if(session.getAttribute("username")==null){
			Error error=new Error(5,"UnAuthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);//401 - 2nd call back func will be executed
		}
		List<Forum> bloglist=forumDAO.getForums(approved);
		
		return new ResponseEntity<List<Forum>>(bloglist,HttpStatus.OK);
	}
	@RequestMapping(value="/getforumtbyid/{fid}",method=RequestMethod.GET)
	public ResponseEntity<?> getForumById(@PathVariable("fid") int blogid,HttpSession session){
		if(session.getAttribute("username")==null){
			Error error=new Error(5,"UnAuthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);//401 - 2nd call back func will be executed
		}
		Forum blogPost=forumDAO.getForumById(blogid);
		System.out.println(blogPost.getFtitle());
		return new ResponseEntity<Forum>(blogPost,HttpStatus.OK);
	}
	
	@RequestMapping(value="/updateforumpost",method=RequestMethod.PUT)
	public ResponseEntity<?> updateForumPost(@RequestBody Forum forumPost,HttpSession session){
		if (session.getAttribute("username") == null) {
            Error error = new Error(5, "Unauthorize'd user");
			return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);
		}	
			try{
				forumDAO.updateForumPost(forumPost);
			return new ResponseEntity<Void>(HttpStatus.OK);
			}
		catch(Exception e){
			Error error=new Error(2,"Cannot insert blog post details");
			return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	@RequestMapping(value="/commentOnforum",method=RequestMethod.POST)
	public ResponseEntity<?> saveforumComment(@RequestBody ForumComment forumComment,HttpSession session)
	{
		if(session.getAttribute("username")==null){
			Error error=new Error(5,"Unauhorized");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		String username=(String) session.getAttribute("username");
		User user=userDAO.validateUsername(username);
		forumComment.setFcommentDate(new Date());
		forumComment.setFcommentedBy(user);
		try{
			forumDAO.addForumComment(forumComment);
			return new ResponseEntity<ForumComment>(forumComment,HttpStatus.OK);
		}catch(Exception e){
			Error error=new Error(1,"Unable to save the blog");
			return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@RequestMapping(value="/getcomments/{fid}", method=RequestMethod.GET)
	public ResponseEntity<?> getforumcomments(@PathVariable("fid") int fid,HttpSession session)
	{
		
		if(session.getAttribute("username")==null){
			Error error=new Error(5,"Unauhorized");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		List<ForumComment> commentlist=forumDAO.getForumComments(fid);

		return new ResponseEntity<List<ForumComment>>(commentlist,HttpStatus.OK);


}
}
