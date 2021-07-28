package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.RegisterRepository;

@Service
public class RegService {
	@Autowired
	private RegisterRepository repo;
	
	public User saveUser(User user) {
		
		return repo.save(user);
	}
	
	public User checkuseremail(String email) {
		return repo.findByEmail(email);
	}
	
	public User checkuseremailnPassword(String email, String password) {
		return repo.findByEmailAndPassword(email, password);
	}
	
}
