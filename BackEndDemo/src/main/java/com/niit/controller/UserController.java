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
		userDAO.registerUser(user);
		User user1=userDAO.validateUsername(user.getUsername());
		System.out.println(user1.getLastname());
		return new ResponseEntity<User>(user,HttpStatus.CREATED);
	}
		/*try{System.out.println("starting of dummy mehod");
		
	
		user.setOnline(false);
			User dummyuser=userDAO.validateUsername(user.getUsername());
			System.out.println("starting of if in dummy user");
			
			if(dummyuser!=null){
				System.out.println("in the if");
				
				Error error=new Error(2,"user name already exist");
				return new ResponseEntity<Error>(error,HttpStatus.NOT_ACCEPTABLE);
			}
			User dummyEmail=userDAO.validateEmail(user.getEmail());
			if(dummyEmail!=null){
				Error error=new Error(3,"Email is already ");
				return new ResponseEntity<Error>(error,HttpStatus.NOT_ACCEPTABLE);
				
			}

			userDAO.registerUser(user);
			return new ResponseEntity<User>(user,HttpStatus.OK);
			
		}
		catch(Exception e){
			System.out.println("in catch block");
			
			Error error=new Error(1,"Unable to register user"+e.getMessage());
			return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}*/
	
	
	@RequestMapping(value="/User/validateUser",method=RequestMethod.POST)
	public ResponseEntity<?> validateUser(@RequestBody User user,HttpSession session){
		System.out.println("in validate user entered");
		User validUser=userDAO.login(user);
		if(validUser==null){
			Error error=new Error(4,"Envalid Username/password...please enter  valid username/pwd");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		validUser.setOnline(true);
		userDAO.updateUser(validUser);
		session.setAttribute("username",user.getUsername());
		return new ResponseEntity<User>(validUser,HttpStatus.OK);
	}
	
	@RequestMapping(value="/User/logout",method=RequestMethod.POST)
	public ResponseEntity<?> logout(HttpSession session){
		System.out.println("In logoutBack end controller");
		String username=(String)session.getAttribute("username");
		System.out.println(username);
		User user=userDAO.validateUsername(username);
		//User username=(User)session.getAttribute("username");
		/*if(username==null){
			System.out.println("wrong user");
			Error error=new Error(3,"unauthorized user");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}*/
		user.setOnline(false);
		System.out.println(user.isOnline());
		userDAO.updateUser(user);
		System.out.println(user.isOnline());
		session.removeAttribute("username");
		
		session.invalidate();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	@RequestMapping(value="/User/updateUser",method=RequestMethod.PUT)
	public ResponseEntity<?> updateUser(@RequestBody User user,HttpSession session){
		if(session.getAttribute("username")==null){
			Error error= new Error(5,"UnAuthorized user");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		try{
			userDAO.updateUser(user);
			System.out.println("updating user info");
			return new ResponseEntity<User>(user,HttpStatus.OK);
		}catch(Exception e){
			Error error=new Error(6,"Unable to edit user profile");
			return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@RequestMapping(value="/User/getUser",method=RequestMethod.GET)

	public ResponseEntity<?>getUser(HttpSession session){
		String username=(String) session.getAttribute("username");
		if(username==null){
			Error error= new Error(5,"UnAuthorized user");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		} 
		//String username=(String)session.getAttribute("username");
		User user=userDAO.validateUsername(username);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}


}
