package com.lucatto.securitytest2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lucatto.securitytest2.model.Users;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private UserDAO userDAO;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users users = userDAO.findByUsername(username);
		if (users==null) throw new UsernameNotFoundException("User 404");
		return new CurrentUser(users);
	}

}
