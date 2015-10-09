package com.foosball.web.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Team implements Serializable {

	private static final long serialVersionUID = 1L;
	@NotNull
	private Integer id;
	@Size(max = 40)
	private String name;
	@Size(max = 40)
	
	public Team(String name) {
		this.name = name;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
