package com.foosball.web.controller.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.foosball.web.bean.jpa.UserEntity;
import com.foosball.web.controller.dao.UserDao;
import com.foosball.web.model.User;

@Repository
public class UserDaoImpl implements UserDao {
	
	@PersistenceContext
	EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserEntity> getAllFoosballusers() {
		Query query = getEntityManager().createQuery("SELECT u from UserEntity u");
		return (List<UserEntity>) query.getResultList();
	}
	
	@Override
	public UserEntity findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserEntity save(User entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserEntity update(User entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserEntity create(User entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

}
