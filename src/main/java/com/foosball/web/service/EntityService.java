package com.foosball.web.service;

import java.util.List;

import com.foosball.web.exception.FoosballException;
import com.foosball.web.model.Team;
import com.foosball.web.model.User;

public interface EntityService { 
	
	List<User> getAllFoosballusers();
	User findById(Integer id);
	User save(User entity);
	User update(User entity);
	User create(User entity) throws FoosballException;
	void delete( Integer id );
	User getUser(String userName);
	
	boolean resetPassword(User user) throws FoosballException;
	
	List<Team> getAllTeams();
}
