package com.foosball.web.controller.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.foosball.web.bean.jpa.TeamBo;
import com.foosball.web.bean.jpa.UserBo;
import com.foosball.web.controller.dao.EntityDao;
import com.foosball.web.model.User;

@Repository
public class EntityDaoImpl implements EntityDao {
	
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
	public List<UserBo> getAllFoosballusers() {
		Query query = getEntityManager().createQuery("SELECT u from UserBo u");
		return (List<UserBo>) query.getResultList();
	}
	
	@Override
	public UserBo getUser(String userName) {
		UserBo user;
		Query query = getEntityManager().createQuery("SELECT u from UserBo u where u.username = :username");
		query.setParameter("username", userName);
		try {
			user = (UserBo) query.getSingleResult();
		} catch (NoResultException e) {
			user = null;
		}
		return user;
	}

	@Override
	public UserBo save(UserBo entity) {
		getEntityManager().persist(entity);
		getEntityManager().flush();
		return entity;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TeamBo> getAllTeams() {
		Query query = getEntityManager().createQuery("SELECT t from TeamBo t");
		return (List<TeamBo>) query.getResultList();
	}
	
	@Override
	public int resetPassword(String username, String password) {
		Query query = getEntityManager().createQuery("UPDATE UserBo u set u.password = :password WHERE u.username = :username");
		query.setParameter("username", username);
		query.setParameter("password", password);
		int count = query.executeUpdate();		
		return count;
	}
	
	@Override
	public UserBo findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserBo update(User entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserBo create(User entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}
}