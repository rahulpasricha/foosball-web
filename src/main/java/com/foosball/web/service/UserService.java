package com.foosball.web.service;

import java.util.List;

import com.foosball.web.model.User;

public interface UserService { 
	List<User> getAllFoosballusers();
	User findById(Integer id);
	User save(User entity);
	User update(User entity);
	User create(User entity);
	void delete( Integer id );
}
