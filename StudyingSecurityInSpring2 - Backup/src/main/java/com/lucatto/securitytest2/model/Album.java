package com.lucatto.securitytest2.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.ToString;

@Entity @Getter @ToString(exclude="id")
public class Album {

	@Id
	private long id;
	private long band_id;
	private String title;
	private String a_yearReleased;
	
	// make your setters with the appropriate regex
	
}
