package com.foosball.web.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.foosball.web.exception.FoosballException;
import com.foosball.web.model.Team;
import com.foosball.web.model.User;
import com.foosball.web.service.EntityService;

@Controller
public class EntityController {

	@Resource
	private EntityService entityService;

	@RequestMapping(value = "/getAllFoosballUsers.fd", method = RequestMethod.GET)
	public @ResponseBody List<User> getAllFoosballusers() {
		return entityService.getAllFoosballusers();
	}

	@RequestMapping(value = "/createFoosballUser.fd", method = RequestMethod.POST)
	public @ResponseBody User create(@RequestBody User user, HttpServletResponse response) throws FoosballException {
		User userObject = null;
		try {
			userObject = entityService.create(user);
		} catch (FoosballException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			try {
				response.getWriter().write(((FoosballException) e).getErrorMessage());
				response.flushBuffer();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return userObject;
	}
	
	@RequestMapping(value = "/getAllTeams.fd", method = RequestMethod.GET)
	public @ResponseBody List<Team> getAllTeams() {
		return entityService.getAllTeams();
	}
	
	@RequestMapping(value = "/resetPassword.fd", method = RequestMethod.POST)
	public @ResponseBody boolean resetPassword(@RequestBody User user, HttpServletResponse response) throws FoosballException {
		try {
			entityService.resetPassword(user);
		} catch (FoosballException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			try {
				response.getWriter().write(((FoosballException) e).getErrorMessage());
				response.flushBuffer();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return true;
	}
	
	@RequestMapping(value = "/foosballuser/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public User findOne(@PathVariable("id") Integer id) {
		return entityService.findById(id);
	}

	@RequestMapping(value = "/foosballuser/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public User update(@PathVariable("id") Integer id, @RequestBody User user) {
		return entityService.update(user);
	}

	@RequestMapping(value = "/foosballuser/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable("id") Integer id) {
		entityService.delete(id);
	}

}
