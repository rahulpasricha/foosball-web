package com.foosball.web.service;

import java.util.List;

import com.foosball.web.model.User;
import com.foosball.web.service.impl.UserAlreadyExistsException;

public interface UserService { 
	List<User> getAllFoosballusers();
	User findById(Integer id);
	User save(User entity);
	User update(User entity);
	User create(User entity) throws UserAlreadyExistsException;
	void delete( Integer id );
	User getUser(String userName);
}
