package com.niit.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.niit.dao.ProfileImageDAO;
import com.niit.dao.UserDAO;
import com.niit.model.ProfileImage;
import com.niit.model.User;
import com.niit.model.Error;


@Controller
public class ProfileImageController {
	
		@Autowired
		private ProfileImageDAO profileImageDAO;
		
		@Autowired
		private UserDAO userDAO;
		
		@RequestMapping(value="/uploadprofile",method=RequestMethod.POST)//to upload the img
		public ResponseEntity<?> uploadProfilePic(@RequestParam CommonsMultipartFile pimage,HttpSession session)
		{
			//User user=(User)session.getAttribute("username");
			String username=(String) session.getAttribute("username");
			User user=userDAO.validateUsername(username);
			if(user==null)
			{
				Error error=new Error(5,"Unauthorized");
				return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
			}
			ProfileImage profileImage=new ProfileImage();
			profileImage.setUsername(user.getUsername());
			profileImage.setPimage(pimage.getBytes());
			profileImageDAO.uploadProfilePic(profileImage);
			return new ResponseEntity<User>(user,HttpStatus.OK);
			
		}
		@RequestMapping(value="/getprofilepic/{username}", method=RequestMethod.GET)//to get the progile pic
		public @ResponseBody byte[] getProfilePic(@PathVariable String username,HttpSession session){
			String usernm=(String) session.getAttribute("username");
			User user=userDAO.validateUsername(usernm);
			if(user==null)
				return null;
			else
			{
				ProfileImage profileImage=profileImageDAO.getProfilePic(username);
				if(profileImage==null)
					return null;
				else
					return profileImage.getPimage();
			}
			
	}
	}

