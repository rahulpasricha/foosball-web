package com.foosball.web.bean.jpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "FOOSBALLUSER", schema = "FOOSBALL")
public class UserBo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	
	@OneToOne
	@JoinColumn(name = "TEAM_ID")
	private TeamBo team;
	
	@ManyToMany(mappedBy = "ratingUser")
	private List<UserRatingBo> ratingUsers;
	
	@ManyToMany(mappedBy = "ratedUser")
	private List<UserRatingBo> ratedUsers;

	public List<UserRatingBo> getRatingUsers() {
		return ratingUsers;
	}

	public void setRatingUsers(List<UserRatingBo> ratingUsers) {
		this.ratingUsers = ratingUsers;
	}

	public List<UserRatingBo> getRatedUsers() {
		return ratedUsers;
	}

	public void setRatedUsers(List<UserRatingBo> ratedUsers) {
		this.ratedUsers = ratedUsers;
	}

	public TeamBo getTeam() {
		return team;
	}

	public void setTeam(TeamBo team) {
		this.team = team;
	}

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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserBo other = (UserBo) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserBo [id=" + id + ", username=" + username + ", firstName=" + firstName + ", lastName=" + lastName
				+ "]";
	}
	
}
