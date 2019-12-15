package com.nikhilkaranjkar.services.springrestservices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import  static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nikhilkaranjkar.services.springrestservices.exception.UserNotFoundException;

@RestController
public class RestUserController {

	@Autowired
	private UserDaoService userDaoService;

	@GetMapping(path = "/users")
	public List<User> getUsers() {
		return userDaoService.findAll();
	}

	@GetMapping(path = "/users/{id}")
	public EntityModel<User> getUser(@PathVariable int id) {
		User user = userDaoService.findOne(id);

		if (user == null) {
			throw new UserNotFoundException("The user does not exist");
		}
		//"all-users", SERVER-PATH +"/users"
		//retrieve all users
		
		EntityModel<User> resource = new EntityModel<User>(user);
		
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getUsers());
		
		resource.add(linkTo.withRel("all-users"));
		
		return resource;
	}

	@DeleteMapping(path = "/users/{id}")
	public User deleteUser(@PathVariable int id) {

		User user = userDaoService.deleteUser(id);

		if (user == null) {
			throw new UserNotFoundException("The user does not exist");
		}

		else {
			return user;
		}
		
		
	}

	@PostMapping(path = "/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User saveduser = userDaoService.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saveduser.getId())
				.toUri();

		return ResponseEntity.created(location).build();

	}

}
