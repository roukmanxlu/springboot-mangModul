package com.minqing.service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
public class HelloController {
	
	@RequestMapping(value="/hello",method=RequestMethod.GET)
	public String hello(String name) {
		
		return String.format("woshi:%s", name);
	}
	
}
