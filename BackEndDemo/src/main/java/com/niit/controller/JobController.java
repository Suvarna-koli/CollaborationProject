package com.niit.controller;

import java.util.Date;

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

import com.niit.dao.JobDAO;
import com.niit.dao.UserDAO;
import com.niit.model.Error;
import com.niit.model.Job;
import com.niit.model.User;

@Controller
public class JobController {
	@Autowired
	private JobDAO jobDAO;
	
	@Autowired
	private UserDAO userDAO;

	@RequestMapping(value = "/saveJob", method = RequestMethod.POST)
	public ResponseEntity<?> saveJob(@RequestBody Job job, HttpSession session){
		System.out.println("in job controller");
		if (session.getAttribute("username") == null) {

			Error error = new Error(5, "Unauthorized user");
			return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);
		}
		String username= (String) session.getAttribute("username");
		User users=userDAO.validateUsername(username);
		
		if (users.getRole().equals("Admin")) {
			try {
				job.setPostedOn(new Date());
				jobDAO.saveJob(job);
				return new ResponseEntity<Job>(job,HttpStatus.OK);
			} catch (Exception e) {

				Error error = new Error(7, "Unable insert job details" + e.getMessage());
				return new ResponseEntity<Error>(error, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		else {
			Error error = new Error(6, "Access Denied..");
			return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);
		}

	}


	@RequestMapping(value = "/showjob", method = RequestMethod.GET)
	public ResponseEntity<?> getAllJobs(HttpSession session) {

		if (session.getAttribute("username") == null) {

			Error error = new Error(5, "Unauthorized user");
			return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);

		}
		List<Job> jobs = jobDAO.getAllJobs();
		return new ResponseEntity<List<Job>>(jobs, HttpStatus.OK);
	}

	@RequestMapping(value = "/getJobById/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getJobById(@PathVariable("id") int id, HttpSession session) {

		if (session.getAttribute("username") == null) {

			Error error = new Error(5, "Unauthorized user");
			return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);
		}

		Job job = jobDAO.getJobById(id);
		return new ResponseEntity<Job>(job, HttpStatus.OK);

	}

}
