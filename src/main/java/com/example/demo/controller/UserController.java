package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.RegService;


@CrossOrigin
@RestController
public class UserController {
	
	@Autowired
	private RegService service;
	
	@PostMapping("/userRegister")
	public User registerUser(@RequestBody User user) throws Exception{
		
		
		String checkEmail = user.getEmail();
			if (checkEmail != null && !"".equals(checkEmail)) {
				User userobj= service.checkuseremail(checkEmail);
					if (userobj != null) {
						throw new Exception (checkEmail + " is already exist");
					}
			}
		

		User userObj=null;
		userObj=service.saveUser(user);
		
		return userObj;
	}
	
	@PostMapping("/userLogin")

	
	public User loginUser(@RequestBody User user) throws Exception {
		
		String tempEmail = user.getEmail();
		String tempPassword = user.getPassword();
			User userobj = null;
				if (tempEmail != null && tempPassword != null) {
					userobj = service.checkuseremailnPassword(tempEmail, tempPassword);
				}
						if (userobj == null) {
							throw new Exception (" Invalid User");
						}
						
			return userobj;
		}
	}

