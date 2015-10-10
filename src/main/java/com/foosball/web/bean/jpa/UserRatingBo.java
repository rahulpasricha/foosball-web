package com.foosball.web.bean.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USER_RATINGS", schema = "FOOSBALL")
public class UserRatingBo {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "RATING_USER_ID")
	private UserBo ratingUser;
	
	@ManyToOne
	@JoinColumn(name = "RATED_USER_ID")
	private UserBo ratedUser;
	
	@Column(name = "RATING")
	private Integer rating;

	public UserBo getRatingUser() {
		return ratingUser;
	}

	public void setRatingUser(UserBo ratingUser) {
		this.ratingUser = ratingUser;
	}

	public UserBo getRatedUser() {
		return ratedUser;
	}

	public void setRatedUser(UserBo ratedUser) {
		this.ratedUser = ratedUser;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}
	
	

}
