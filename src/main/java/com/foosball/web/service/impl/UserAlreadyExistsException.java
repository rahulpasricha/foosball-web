package com.foosball.web.service.impl;

public class UserAlreadyExistsException extends Exception {
	
	private static final long serialVersionUID = 5176493686091199961L;

	String errorMessage;
	
	UserAlreadyExistsException (String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
