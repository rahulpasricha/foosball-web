package com.foosball.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foosball.web.bean.jpa.TeamBo;
import com.foosball.web.bean.jpa.UserBo;
import com.foosball.web.controller.dao.EntityDao;
import com.foosball.web.exception.FoosballException;
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
