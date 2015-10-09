package com.foosball.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foosball.web.bean.jpa.UserEntity;
import com.foosball.web.controller.dao.UserDao;
import com.foosball.web.model.User;
import com.foosball.web.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Resource
	UserDao userDao;
	
	@Override
	public List<User> getAllFoosballusers() {
		List<User> users = new ArrayList<User>();
		
		List<UserEntity> entities = userDao.getAllFoosballusers();
		for (UserEntity entity : entities) {
			User user = new User();
			user.setFirstName(entity.getFirstName());
			user.setLastName(entity.getLastName());
			users.add(user);
		}
		
		return users;
	}
	
	@Override
	public User getUser(String userName) {
		UserEntity userEntity = userDao.getUser(userName);
		
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
	public User create(User user) throws UserAlreadyExistsException {
		UserEntity userEntity = userDao.getUser(user.getUsername());
		
		if (userEntity != null) {
			throw new UserAlreadyExistsException("User already present in the system");
		}
		
		userEntity = new UserEntity();
		userEntity.setUsername(user.getUsername());
		userEntity.setPassword(user.getPassword());
		userEntity.setFirstName(user.getFirstName());
		userEntity.setLastName(user.getLastName());
		userEntity.setRole("ROLE_USER");
		userEntity.setLevel(0);
		
		userDao.save(userEntity);
		
		return user;
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
