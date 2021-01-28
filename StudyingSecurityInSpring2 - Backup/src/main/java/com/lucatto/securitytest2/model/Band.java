package com.lucatto.securitytest2.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.ToString;

@Entity @Getter @ToString(exclude="id")
public class Band {

	@Id
	private long id;
	private String title;
	private String b_yearFormation;
	
	// make your setters with the appropriate regex
	
}
