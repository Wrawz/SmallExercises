package com.lucatto.securitytest2.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.ToString;

@Entity @Getter @ToString(exclude="id")
public class Song {
	
	@Id
	private long id;
	private String title;
	private String Album_id;
	private String s_length;
	
	public boolean setTitle(String title) {
		if (title.matches("$\\w+(\\s\\w+)*^")) {
			this.title = title;
			return true;
		}
		return false;
	}
	
	public boolean setS_length(String length) {
		if (length.matches("$[0-9]{2}:[0-9]{2}^")) {
			this.s_length = length;
			return true;
		}
		return false;
	}

}
