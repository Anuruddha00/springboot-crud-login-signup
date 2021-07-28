package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

@Repository
public interface RegisterRepository extends JpaRepository<User,Integer> {

	public User findByEmail(String email);
	
	public User findByEmailAndPassword(String email, String password);
	
}
