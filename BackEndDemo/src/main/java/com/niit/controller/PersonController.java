package com.niit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.niit.dao.PersonDAO;
import com.niit.model.Person;

@Controller
public class PersonController {
	@Autowired
	PersonDAO personDAO;
	
	@RequestMapping(value="/Person/getallPersons",method=RequestMethod.GET)
	public @ResponseBody List<Person> getAllPersons()
	{
		List<Person> persons=personDAO.getAllPersons();
		System.out.println(persons);
		return persons;
		
	}

}
