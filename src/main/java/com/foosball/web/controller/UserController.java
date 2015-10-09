package com.foosball.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.foosball.web.model.User;
import com.foosball.web.service.UserService;

@Controller
public class UserController {

	@Resource
	private UserService userService;

	@RequestMapping(value = "/getAllfoosballUsers.fd", method = RequestMethod.GET)
	@ResponseBody
	public List<User> getAllFoosballusers() {
		return userService.getAllFoosballusers();
	}

	@RequestMapping(value = "/foosballuser/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public User findOne(@PathVariable("id") Integer id) {
		return userService.findById(id);
	}

	@RequestMapping(value = "/foosballuser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public User create(@RequestBody User user) {
		return userService.create(user);
	}

	@RequestMapping(value = "/foosballuser/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public User update(@PathVariable("id") Integer id, @RequestBody User user) {
		return userService.update(user);
	}

	@RequestMapping(value = "/foosballuser/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable("id") Integer id) {
		userService.delete(id);
	}

}
