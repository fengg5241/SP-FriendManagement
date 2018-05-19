package com.sp.friend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sp.friend.model.SPResponse;
import com.sp.friend.service.UserService;

@RestController
public class UserController {
	@Autowired
	UserService userService;

	@RequestMapping("/addUser")
	public SPResponse addUser(@RequestParam(value = "email") String email) {
		userService.save(email);
		return new SPResponse();
	}
	
	@RequestMapping("/getUser")
	public SPResponse getUser(@RequestParam(value = "email") String email) {
		userService.retrieve(email);
		return new SPResponse();
	}


}
