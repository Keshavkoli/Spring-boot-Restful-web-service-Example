package com.javatpoint.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.javatpoint.dao.UserDaoService;
import com.javatpoint.exceptionhandler.UserNotFoundException;
import com.javatpoint.model.User;
import com.javatpoint.repository.UserRepository;

@RestController
public class UserJPAResource {
	@Autowired
	private UserDaoService service;

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/jpa/users")
	public List<User> retriveAllUsers() {
		return userRepository.findAll();
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

	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}

	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User sevedUser = service.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(sevedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
}
