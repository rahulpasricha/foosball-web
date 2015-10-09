package com.foosball.web.controller.dao;

import java.util.List;

import com.foosball.web.bean.jpa.UserEntity;
import com.foosball.web.model.User;

public interface UserDao {
	
	List<UserEntity> getAllFoosballusers();
	UserEntity findById(Integer id);
	UserEntity save(UserEntity userEntity);
	UserEntity update(User entity);
	UserEntity create(User entity);
	void delete( Integer id );
	UserEntity getUser(String userName);
}
