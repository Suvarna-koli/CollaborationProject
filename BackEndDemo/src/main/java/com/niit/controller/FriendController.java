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
	
	@RequestMapping(value="/getSuggestedUser",method=RequestMethod.GET)
	public ResponseEntity<?> getListOfSuggestedUser(HttpSession session){
		System.out.println("in frind controller for suggest fun");
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
	
}
