package com.foosball.web.controller.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.foosball.web.bean.jpa.TeamBo;
import com.foosball.web.bean.jpa.UserBo;
import com.foosball.web.bean.jpa.UserRatingBo;
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
	public UserBo getUser(Integer id) {
		UserBo user;
		Query query = getEntityManager().createQuery("SELECT u from UserBo u where u.id = :id");
		query.setParameter("id", id);
		try {
			user = (UserBo) query.getSingleResult();
		} catch (NoResultException e) {
			user = null;
		}
		return user;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UserBo> otherUsers(String username) {
		Query query = getEntityManager().createQuery("SELECT u from UserBo u where u.username != :username and u.role = :role")
						.setParameter("username", username)
						.setParameter("role", "ROLE_USER");
		return (List<UserBo>) query.getResultList();
	}


	@Override
	public UserBo save(UserBo entity) {
		getEntityManager().persist(entity);
		getEntityManager().flush();
		return entity;
	}
	
	@Override
	public void save(UserRatingBo userRatings) {
		getEntityManager().persist(userRatings);
		getEntityManager().flush();
	}
	
	@Override
	public UserRatingBo getUserRating(int ratedUserId, int ratingUserId) {
		UserRatingBo userRating;
		Query query = getEntityManager().createQuery("from UserRatingBo u where u.ratedUser.id =:ratedUserId and u.ratingUser.id =:ratingUserId");
		query.setParameter("ratedUserId", ratedUserId).setParameter("ratingUserId", ratingUserId);
		try {
			userRating = (UserRatingBo) query.getSingleResult();
		} catch (NoResultException e) {
			userRating = null;
		}
		return userRating;
	}
	
	@Override
	public List<UserRatingBo> getUserRatings(String username) {
		Query query = getEntityManager().createQuery("from UserRatingBo u where u.ratingUser.username =:username").setParameter("username", username);
		return (List<UserRatingBo>) query.getResultList();
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<TeamBo> getAllTeams() {
		Query query = getEntityManager().createQuery("SELECT t from TeamBo t order by id");
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
	public String getLatestJsonResultSet() {
		return getEntityManager().createNativeQuery("").getSingleResult().toString();
	}
	
	@Override
	public String getJsonResultSet() {
		return (String) getEntityManager().createNativeQuery("SELECT JSONRESULTSTRING FROM FOOSBALL.JSON_RESULTSET").getSingleResult();
	}

	@Override
	public String updateJsonResultSet(String json) {
		getEntityManager().createNativeQuery("UPDATE FOOSBALL.JSON_RESULTSET SET JSONRESULTSTRING = :json, CREATEDON = :today")
			.setParameter("json", json).setParameter("today", new Date()).executeUpdate();
		
		return getJsonResultSet();
	}

	@Override
	public String getFlagToAllowRatingUpdate() {
		return (String) getEntityManager().createNativeQuery("SELECT VALUE FROM FOOSBALL.APPCONFIG WHERE NAME = 'ALLOW_RATINGS_UPDATE'").getSingleResult();
	}

	@Override
	public String getFlagToAllowTeamNameUpdate() {
		return (String) getEntityManager().createNativeQuery("SELECT VALUE FROM FOOSBALL.APPCONFIG WHERE NAME = 'ALLOW_TEAM_UPDATE'").getSingleResult();
	}
	
	@Override
	public String getFlagToAllowCreateUser() {
		return (String) getEntityManager().createNativeQuery("SELECT VALUE FROM FOOSBALL.APPCONFIG WHERE NAME = 'ALLOW_CREATE_USER'").getSingleResult();
	}
	
	@Override
	public int updateFlag(String flag, String value) {
		return getEntityManager().createNativeQuery("UPDATE FOOSBALL.APPCONFIG SET VALUE = :value WHERE NAME = :name")
			.setParameter("value", value)
			.setParameter("name", flag).executeUpdate();
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
