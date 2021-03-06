package com.niit.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.dao.FriendDAO;
import com.niit.dao.UserDAO;
import com.niit.model.Error;
import com.niit.model.Friend;
import com.niit.model.User;

@Controller
public class FriendController {
	@Autowired
	FriendDAO friendDAO;

	@Autowired
	UserDAO userDAO;
	
	@RequestMapping(value="/getSuggestedUser",method=RequestMethod.GET)//to the suggested users
	public ResponseEntity<?> getListOfSuggestedUser(HttpSession session){
		
		if(session.getAttribute("username")==null){
			 Error error = new Error(5, "Unauthorized user");
				return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);
		}
		String username=(String)session.getAttribute("username");
		System.out.println(username);
		List<User> suggestedUser=friendDAO.getListOfSuggUser(username);
		System.out.println(suggestedUser);
		return new ResponseEntity<List<User>>(suggestedUser,HttpStatus.OK);
		
	}
	@RequestMapping(value="/addfriendrequest/{toId}",method=RequestMethod.POST)//to send the request
	public ResponseEntity<?> addFriendRequest(@PathVariable("toId") String toId,HttpSession session){
		System.out.println("send friend request"+toId);
		if(session.getAttribute("username")==null){
			 Error error = new Error(5, "Unauthorized user");
				return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);
		}
		String username=(String)session.getAttribute("username");
friendDAO.addFriendRequest(username, toId);
return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/getpendingrequest",method=RequestMethod.GET)//to get the received requests
	public ResponseEntity<?> getPendingRequest(HttpSession session){
		if(session.getAttribute("username")==null){
			 Error error = new Error(5, "Unauthorized user");
				return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);
		}
		String username=(String)session.getAttribute("username");
		List<Friend> pendingRequest=friendDAO.getPendingRequest(username);
		return new ResponseEntity<List<Friend>>(pendingRequest,HttpStatus.OK);
	}
	
	@RequestMapping(value="/updatependingrequest",method=RequestMethod.PUT)//when  accept the  request it shoud remove that user and update
	public ResponseEntity<?> updatePendingRequest(@RequestBody Friend pendingRequest,HttpSession session){
		if(session.getAttribute("username")==null){
			 Error error = new Error(5, "Unauthorized user");
				return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);
		}
		friendDAO.updatePendingRequest(pendingRequest);
		return new ResponseEntity<Friend>(pendingRequest,HttpStatus.OK);
	}
	
	@RequestMapping(value="/getuserdetails/{fromId}",method=RequestMethod.GET)//get the user details before accepting request
	public ResponseEntity<?> getUserDetails(@PathVariable("fromId") String fromId,HttpSession session){
		if(session.getAttribute("username")==null){
			 Error error = new Error(5, "Unauthorized user");
				return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);
		}

		User user=userDAO.validateUsername(fromId);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	@RequestMapping(value="/getfriendlist", method=RequestMethod.GET)//to get the list of frnds
	public ResponseEntity<?> listOfFriends(HttpSession session)
	{
		if(session.getAttribute("username")==null){
			Error error=new Error(5,"Unauhorized");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		
		String username=(String) session.getAttribute("username");
		List<Friend> friendlist=friendDAO.listOfFriends(username);
		return new ResponseEntity<List<Friend>>(friendlist,HttpStatus.OK);
	}
}
