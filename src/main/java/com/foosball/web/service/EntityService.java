package com.foosball.web.service;

import java.util.List;

import com.foosball.web.exception.FoosballException;
import com.foosball.web.model.Rating;
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
	
	List<Rating> getUsersToRate(String username);
	boolean updateUserRatings(String username, List<Rating> ratings);
	String getFoosballScores();
	
	String getJsonResultSet();
	String updateJsonResultSet(String json);
	
	String getFlagToAllowRatingUpdate();
	String getFlagToAllowTeamNameUpdate();
	String getFlagToAllowCreateUser();
	int updateFlag(String flag, String value);
	
	Integer buildTeam();
	
	String getTeamName(String username);
	boolean updateTeamName(String username, String teamName);
}
