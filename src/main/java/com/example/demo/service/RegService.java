package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.RegisterRepository;
import com.example.demo.response.ResponseEP;


@Service
public class RegService {
	@Autowired
	private RegisterRepository repo;
	
	public ResponseEP saveUser(User user) {
		ResponseEP res = null;
		try {
			//service + business logic
			String checkEmail = user.getEmail();
			if(checkEmail !=null && !"".equals(checkEmail)) {
				User userObj = checkuseremail(checkEmail);
				if(userObj !=null) {
					res = new ResponseEP();
					res.setMessageString("User already exist");
					res.setStatusCode(500);
					res.setUserid(userObj.getId());
					}else {
						repo.save(user);
						res = new ResponseEP();
						res.setMessageString("User saved successfully");
						res.setStatusCode(200);
						res.setUserid(1);
					}
				}
			}catch(Exception ex) {
				// what is your error response
				res = new ResponseEP();
				res.setMessageString("Error in user saving");
				res.setStatusCode(405);
				res.setUserid(0);
				
		}
		return res;
		
	}
	
	public User checkuseremail(String email) {
		return repo.findByEmail(email);
	}
	
	public User checkuseremailnPassword(String email, String password) {
		return repo.findByEmailAndPassword(email, password);
	}
	
   
	
}
