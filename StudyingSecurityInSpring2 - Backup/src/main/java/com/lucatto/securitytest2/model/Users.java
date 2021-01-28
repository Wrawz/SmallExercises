package com.lucatto.securitytest2.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity @ToString(exclude="id")
public class Users {
	
	@Id
	private long id;
	@Getter
	private String real_name; // idk why, but "realName" didn't work when setting up database with workbench
	@Getter
	private String rg;
//	@Getter
	private String username;
//	@Getter
	private String u_password;
	
	
	
	public void setId(int id) {
		return;
//		set up your code
//		this.id = id;
	}
	
	public void setName(String real_name) {
		return;
		// set up your regex and whatever else you need
		// this.real_name = real_name;
	}
	
	public void setRg(String rg) {
		return;
		// set up your regex and whatever else you need
		// this.rg = rg;
	}
	
	public void setUsername(String username) {
		return;
		// set up your regex and whatever else you need
		// this.username = username;
	}
	
	public void setPassword(String u_password) {
		return;
		// set up your regex and whatever else you need
		// this.u_password = u_password;
	}

	public String getU_password() {
		// TODO Auto-generated method stub
		return u_password;
	}

	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

}
