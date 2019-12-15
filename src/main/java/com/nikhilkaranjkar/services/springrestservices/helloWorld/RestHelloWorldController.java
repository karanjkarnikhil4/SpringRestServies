package com.nikhilkaranjkar.services.springrestservices.helloWorld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestHelloWorldController {

	
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(method = RequestMethod.GET, path = "/hello-World")
	public String helloWorldWithRequestMapping() {

		return "hello-World";

	}

	@GetMapping(path = "/helloWorld")
	public String helloWorld() {

		return "hello-World";

	}
	
	@GetMapping(path = "/helloWorldBean")
	public HelloWorldBean helloWorldBean() {

		return new HelloWorldBean("this is from Hello World Bean");

	}
	
	

	@GetMapping(path = "/helloWorld/path-Variable/{name}")
	public String helloWorldWithPathVariable(@PathVariable String name) {

		return String.format("hello-World %s", name);

	}
	
	@GetMapping(path = "/helloWorld-inter")
	public String helloWorldInternationalization(@RequestHeader(name = "Accept-Language",required = false) Locale locale) {

		return messageSource.getMessage("good.morning.message",null, locale);

	}

}
