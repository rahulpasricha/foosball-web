package com.foosball.web.controller.dao;

import java.util.List;

import com.foosball.web.bean.jpa.TeamBo;
import com.foosball.web.bean.jpa.UserBo;
import com.foosball.web.model.User;

public interface EntityDao {
	
	List<UserBo> getAllFoosballusers();
	UserBo findById(Integer id);
	UserBo save(UserBo userEntity);
	UserBo update(User entity);
	UserBo create(User entity);
	void delete( Integer id );
	UserBo getUser(String userName);
	
	int resetPassword(String username, String password);
	
	List<TeamBo> getAllTeams();
	UserBo getUser(Integer id);
	List<UserBo> otherUsers(String username);
}
