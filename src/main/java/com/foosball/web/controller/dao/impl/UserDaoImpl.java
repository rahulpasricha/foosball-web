package com.foosball.web.controller.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
	public UserEntity getUser(String userName) {
		UserEntity user;
		Query query = getEntityManager().createQuery("SELECT u from UserEntity u where u.username = :username");
		query.setParameter("username", userName);
		try {
			user = (UserEntity) query.getSingleResult();
		} catch (NoResultException e) {
			user = null;
		}
		return user;
	}

	@Override
	public UserEntity save(UserEntity entity) {
		getEntityManager().persist(entity);
		getEntityManager().flush();
		return entity;
	}
	
	@Override
	public UserEntity findById(Integer id) {
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
