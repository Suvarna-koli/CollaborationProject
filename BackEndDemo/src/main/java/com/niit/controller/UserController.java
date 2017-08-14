package com.niit.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.UserDAO;
import com.niit.model.*;
import com.niit.model.Error;

@RestController
public class UserController {

	@Autowired
	private UserDAO userDAO;
	
	@RequestMapping(value="/User/registerUser",method=RequestMethod.POST)
	public ResponseEntity<?> registerUser(@RequestBody User user)
	{
		System.out.println("entered");
		/*userDAO.registerUser(user);
		return new ResponseEntity<User>(user,HttpStatus.OK);
		*/
		try{System.out.println("starting of dummy mehod");
		
			User dummyuser=userDAO.validateUsername(user.getUsername());
			System.out.println("starting of if in dummy user");
			
			if(dummyuser!=null){
				System.out.println("in the if");
				
				Error error=new Error(2,"user name already exist");
				return new ResponseEntity<Error>(error,HttpStatus.NOT_ACCEPTABLE);
			}
			/*User dummyEmail=userDAO.validateEmail(user.getEmail());
			if(dummyEmail!=null){
				Error error=new Error(3,"Email is already ");
				return new ResponseEntity<Error>(error,HttpStatus.NOT_ACCEPTABLE);
				
			}*/

			userDAO.registerUser(user);
			return new ResponseEntity<User>(user,HttpStatus.OK);
			
		}
		catch(Exception e){
			System.out.println("in catch block");
			
			Error error=new Error(1,"Unable to register user");
			return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/User/validateUser",method=RequestMethod.POST)
	public ResponseEntity<?> validateUser(@RequestBody User user){
		System.out.println("in validate user entered");
		User validUser=userDAO.login(user);
		if(validUser==null){
			Error error=new Error(4,"Envalid Username/password...please enter  valid username/pwd");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		validUser.setOnline(true);
		userDAO.updateUser(validUser);
		return new ResponseEntity<User>(validUser,HttpStatus.OK);
	}
	
	@RequestMapping(value="/User/logout",method=RequestMethod.POST)
	public ResponseEntity<?> logout(HttpSession session){
		String username=(String)session.getAttribute("username");
		User user=userDAO.getUserByUsername(username);
		user.setOnline(false);
		userDAO.updateUser(user);
		session.removeAttribute(username);
		session.invalidate();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}


}
