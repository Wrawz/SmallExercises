package com.lucatto.securitytest2;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucatto.securitytest2.model.Users;

public interface UserDAO extends JpaRepository<Users, Long> {
	
	Users findByUsername(String username);

}
