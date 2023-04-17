package com.javatpoint.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javatpoint.versioning.Name;
import com.javatpoint.versioning.PersonV1;
import com.javatpoint.versioning.PersonV2;
//@RestController
public class PersonVersoningController {
	// this method is for the first version that returns the entire name
	@GetMapping("v1/person")
	public PersonV1 personv1() {
		return new PersonV1("Tom Cruise");
	}

	// this method is for the second version that returns firstName and lastName
	// separately
	@GetMapping("v2/person")
	public PersonV2 personv2() {
		return new PersonV2(new Name("Tom", "Cruise"));
	}
}
