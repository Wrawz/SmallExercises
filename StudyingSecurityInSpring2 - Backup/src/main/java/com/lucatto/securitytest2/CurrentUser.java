package com.lucatto.securitytest2;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.lucatto.securitytest2.model.Users;

public class CurrentUser implements UserDetails{
	
	/**
	 * 
	 */
//	private static final long serialVersionUID = 1L;
	private Users user;

	public CurrentUser(Users user) {
		super();
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority("USERS"));
	}
	
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getU_password();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
