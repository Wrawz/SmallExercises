package com.lucatto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lucatto.securitytest2.UserDAO;
import com.lucatto.securitytest2.model.Users;

@Controller
public class UsersController {

	@Autowired
	UserDAO userDAO;
	
	@GetMapping("users")
	@ResponseBody
	public List<Users> getUsers() {
		List<Users> users = userDAO.findAll();
		return users;
		
	}
	
	@GetMapping("users/{id}")
	@ResponseBody
	public Users getUsers(@PathVariable("id") long id) {
		Users users = userDAO.findById(id).orElse(new Users());
		return users;
		
	}
}
