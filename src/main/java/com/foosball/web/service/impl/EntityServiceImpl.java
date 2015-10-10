package com.foosball.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foosball.web.bean.jpa.TeamBo;
import com.foosball.web.bean.jpa.UserBo;
import com.foosball.web.bean.jpa.UserRatingBo;
import com.foosball.web.controller.dao.EntityDao;
import com.foosball.web.exception.FoosballException;
import com.foosball.web.model.Rating;
import com.foosball.web.model.Team;
import com.foosball.web.model.User;
import com.foosball.web.service.EntityService;

@Service("entityService")
@Transactional
public class EntityServiceImpl implements EntityService {

	@Resource
	EntityDao entityDao;
	
	@Override
	public List<User> getAllFoosballusers() {
		List<User> users = new ArrayList<User>();
		
		List<UserBo> entities = entityDao.getAllFoosballusers();
		for (UserBo entity : entities) {
			User user = new User();
			
			if ("admin".equals(entity.getUsername())) {
				continue;
			}
			
			user.setFirstName(entity.getFirstName());
			user.setLastName(entity.getLastName());
			users.add(user);
		}
		
		return users;
	}
	
	@Override
	public User getUser(String userName) {
		UserBo userEntity = entityDao.getUser(userName);
		
		if (userEntity == null) {
			return null;
		}
		
		User user = new User();
		user.setUsername(userEntity.getUsername());
		user.setFirstName(userEntity.getFirstName());
		user.setLastName(userEntity.getLastName());
		user.setPassword(userEntity.getPassword());
		user.setAuthorities(AuthorityUtils.createAuthorityList(userEntity.getRole()));
		
		return user;
	}
	
	@Override
	public User create(User user) throws FoosballException {
		UserBo userEntity = entityDao.getUser(user.getUsername());
		
		if (userEntity != null) {
			throw new FoosballException("User already present in the system");
		}
		
		userEntity = new UserBo();
		userEntity.setUsername(user.getUsername());
		userEntity.setPassword(user.getPassword());
		userEntity.setFirstName(user.getFirstName());
		userEntity.setLastName(user.getLastName());
		userEntity.setRole("ROLE_USER");
		userEntity.setLevel(0);
		
		entityDao.save(userEntity);
		
		return user;
	}
	
	@Override
	public List<Team> getAllTeams() {
		List<TeamBo> teams = entityDao.getAllTeams();
		
		List<Team> allTeams = new ArrayList<Team>();
		for (TeamBo eachTeam : teams) {
			allTeams.add(new Team(eachTeam.getName()));
		}
		
		return allTeams;
	}
	
	@Override
	public List<Rating> getUsersToRate(String username) {
		UserBo user = entityDao.getUser(username);
		if (ArrayUtils.isEmpty(user.getRatedUsers().toArray())) {
			return createDefaultRatings(entityDao.otherUsers(username));
		}
		return convertRatingBoToVo(user.getRatedUsers());
	}
	
	@Override
	public boolean updateUserRatings(String username, List<Rating> ratings) {
		UserBo ratingUserBo = entityDao.getUser(username);
		if (ratingUserBo == null) {
			return false;
		}
		for (Rating rating : ratings) {
			UserBo ratedUserBo = entityDao.getUser(rating.getUserId());
			UserRatingBo userRating = new UserRatingBo();
			userRating.setRatedUser(ratedUserBo);
			userRating.setRatingUser(ratingUserBo);
			if (ratingUserBo.getRatedUsers().contains(userRating)) {
				int ratedIndex = ratingUserBo.getRatedUsers().indexOf(userRating);
				userRating = ratingUserBo.getRatedUsers().get(ratedIndex);
			} else {
				ratingUserBo.getRatedUsers().add(userRating);
			}
			userRating.setRating(rating.getRating());
		}
		entityDao.save(ratingUserBo);
		return true;
	}
	
	private List<Rating> convertRatingBoToVo(List<UserRatingBo> ratedUsers) {
		List<Rating> ratings = new ArrayList();
		for (UserRatingBo userRating : ratedUsers) {
			Rating rating = new Rating();
			rating.setUserId(userRating.getRatedUser().getId());
			rating.setFoosballPlayerName(String.format("%s %s", userRating.getRatedUser().getFirstName(), userRating.getRatedUser().getLastName()));
			rating.setRating(userRating.getRating());
			ratings.add(rating);
		}
		return ratings;
	}

	private List<Rating> createDefaultRatings(List<UserBo> otherUsers) {
		List<Rating> ratings = new ArrayList();
		for (UserBo user : otherUsers) {
			Rating userRating = new Rating();
			userRating.setUserId(user.getId());
			userRating.setFoosballPlayerName(String.format("%s %s", user.getFirstName(), user.getLastName()));
			ratings.add(userRating);
		}
		return ratings;
	}
	
	@Override
	public boolean resetPassword(User user) throws FoosballException {
		int count = entityDao.resetPassword(user.getUsername(), user.getPassword());
		
		if (count == 0) {
			throw new FoosballException("Reset password failed. Entered username doesn't exists.");
		}
		return true;
	}
	
	@Override
	public User findById(Integer id) {
		return null;
	}

	@Override
	public User save(User entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User update(User entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

}
