package com.nikhilkaranjkar.services.springrestservices.helloWorld;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SpringMVCHelloWorldController {
	
	@RequestMapping("/MvcHello")
	@ResponseBody
	public String SpringMvcHelloWorld()
	{
		return "Hello World From Spring Mvc";
	}

}
