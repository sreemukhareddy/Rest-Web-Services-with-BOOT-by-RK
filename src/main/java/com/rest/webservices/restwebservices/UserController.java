package com.rest.webservices.restwebservices;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class UserController {
	
	@Autowired
	private UserRepositoryService service;
	
	@GetMapping("/users")
	public List<User> getUsers(){
		return service.getAllUsers();
	}
	
	/*@GetMapping("/filteredusers")
	public MappingJacksonValue getFilteredUsers(){
		List<User> users=service.getAllUsers();
		SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter.filterOutAllExcept("id","name");
		FilterProvider filters=new SimpleFilterProvider().addFilter("UserFilter", filter);
		MappingJacksonValue jacksonValue=new MappingJacksonValue(users);
		jacksonValue.setFilters(filters);
		return jacksonValue;
	}*/
	
	@GetMapping("/users/{id}")
	public User getUser(@PathVariable Integer id) {
		return service.findUser(id);
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> saveUSer(@Valid @RequestBody User user) {
		service.saveUser(user);
		/*return "Success";*/
		
		URI location = ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(user.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{id}")
	public User deleteUser(@PathVariable int id) {
		return service.deleteUser(id);
	}

}
