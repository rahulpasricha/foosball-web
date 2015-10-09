package com.foosball.web.handler;

import javax.annotation.Resource;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.foosball.web.model.User;
import com.foosball.web.service.UserService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Resource
	private UserService userService;

	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		String userName = authentication.getName().trim();
		String inputPassword = authentication.getCredentials().toString()
				.trim();

		User user = userService.getUser(userName);

		if (user == null) {
			throw new BadCredentialsException("User name not found");
		}

		if (!inputPassword.equals(user.getPassword())) {
			throw new BadCredentialsException("Password is incorrect");
		}

		return new UsernamePasswordAuthenticationToken(user, inputPassword,
				user.getAuthorities());
	}

	@Override
	public boolean supports(Class<? extends Object> authentication) {
		return true;
	}
}