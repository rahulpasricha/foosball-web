package com.foosball.web.bean.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "FOOSBALLUSER", schema = "FOOSBALL")
@SequenceGenerator(name="FOOSBALLUSER_SEQUENCE_GENERATOR", sequenceName="SQ_FOOSBALLUSER")
public class UserBo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(generator = "FOOSBALLUSER_SEQUENCE_GENERATOR")
	@Column(name = "ID", nullable = false)
	private Integer id;
	
	@Column(name = "USERNAME", length = 40)
	private String username;
	
	@Column(name = "PASSWORD", length = 40)
	private String password;
	
	@Column(name = "FIRSTNAME", length = 40)
	private String firstName;

	@Column(name = "LASTNAME", length = 40)
	private String lastName;
	
	@Column(name = "LEVEL", nullable = true)
	private Integer level;
	
	@Column(name = "ROLE", length = 40)
	private String role;

	public UserBo() {
		super();
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
}
