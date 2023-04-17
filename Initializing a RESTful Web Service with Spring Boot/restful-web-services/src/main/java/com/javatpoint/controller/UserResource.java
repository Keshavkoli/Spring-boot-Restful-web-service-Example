package com.javatpoint.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.javatpoint.dao.UserDaoService;
import com.javatpoint.exceptionhandler.UserNotFoundException;
import com.javatpoint.model.User;

public class UserResource {

	@Autowired
	private UserDaoService service;

	@GetMapping("/users")
	public List<User> retriveAllUsers() {
		return service.findAll();
	}

	@GetMapping("/users/{id}")
	public Resource<User> retriveUser(@PathVariable int id) {
		User user = service.findOne(id);
		if (user == null)
			// runtime exception
			throw new UserNotFoundException("id: " + id);
		// "all-users", SERVER_PATH + "/users"
		// retrieveAllUsers
		Resource<User> resource = new Resource<User>(user); // constructor of Resource class
		// add link to retrieve all the users
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retriveAllUsers());
		resource.add(linkTo.withRel("all-users"));
		return resource;
	}
}
