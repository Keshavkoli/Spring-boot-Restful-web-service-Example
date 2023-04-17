package com.javatpoint.controller;

import java.net.URI;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.javatpoint.dao.UserDaoService;
import com.javatpoint.exceptionhandler.UserNotFoundException;
import com.javatpoint.model.FamilyMembers;
import com.javatpoint.model.HelloWorldBean;
import com.javatpoint.model.User;

@Configuration
//@RestController
public class HelloWorldController {
	@Autowired
	private MessageSource messageSource;

	@Autowired
	private UserDaoService service;

	@RequestMapping(path = "/hello-world")
	public String helloWorld() {
		return "Hello World";
	}

//	@RequestMapping(method=RequestMethod.GET, path="/hello-world")  
	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World"); // constructor of HelloWorldBean
	}

	@GetMapping(path = "/family-member")
	public FamilyMembers familyMember() {
		return new FamilyMembers("Satyanarayan KOLI", "Rupa KOLI", "Kartik Koli", "Kaveri Koli", "Bassamma Koli"); // constructor
																													// of
																													// HelloWorldBean
	}

	@GetMapping(path = "/hello-world/path-variable/{name}") // from here we can change the name
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World, %s", name)); // %s replace the name
	}

	@GetMapping("/users")
	public List<User> retriveAllUsers() {
		return service.findAll();
	}

	// retrieves a specific user detail by giving the id

	@GetMapping("/users/{id}")
	public User retriveUser(@PathVariable int id) {
		User user = service.findOne(id);
		if (user == null)
			// runtime exception
			throw new UserNotFoundException("id: " + id);
		return user;
	}

	/*
	 * Suppose, we have requested a GET request for localhost:8080/users/1, it
	 * returns the details of user id 1. Along with this, it also returns a field
	 * called link that contains a link (localhost:8080/users) of all users so that
	 * consumers can retrieve all the users. This concept is called HATEOAS. below
	 * is the example of HATEOAS
	 */
	/*
	 * Example { "id": 2, "name": "Robert", "dob": "2023-04-15T04:08:12.265+0000",
	 * "_links": { "all-users": { "href": "http://localhost:8080/users" } } }
	 */

	/*
	 * //retrieves a specific user detail
	 * 
	 * @GetMapping("/users/{id}") public User retriveUser(@PathVariable int id) {
	 * return service.findOne(id); }
	 */

	// method that delete a user resource
	// if the user deleted successfully it returns status 200 OK otherwise 404 Not
	// Found
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = service.deleteById(id);
		if (user == null)
			// runtime exception
			throw new UserNotFoundException("id: " + id);
	}

	/*
	 * We need to configure two things to make the service internationalized.
	 * -LocaleResolver -ResourceBundleMessageSource below is the example of
	 * internationalized
	 * 
	 * usage -AutowireMessageSource
	 * 
	 * @RequestHeader(value="Accept-language",required=false)Locale locale
	 * -messageSource.getMessage("helloWorld.message",null,locale)
	 */

	// internationalization
	@GetMapping(path = "/hello-world-internationalized")
	public String helloWorldInternationalized(
			@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
		return messageSource.getMessage("good.morning.message", null, locale);
	}

	// method that posts a new user detail
	/*
	 * @PostMapping("/users") public void createUser(@RequestBody User user) { User
	 * sevedUser = service.save(user); }
	 */

	// method that posts a new user detail and returns the status of HTTP and
	// location of the user resource
	// On the right-hand side of the window of postman, we can see the Status: 201
	// Created. It means resource has been properly created

	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User sevedUser = service.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(sevedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
}
