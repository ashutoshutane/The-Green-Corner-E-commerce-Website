package com.qsp.green_corner.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qsp.green_corner.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByemail(String email);

}
