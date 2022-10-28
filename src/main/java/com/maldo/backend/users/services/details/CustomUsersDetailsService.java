package com.maldo.backend.users.services.details;

import com.maldo.backend.users.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class CustomUsersDetailsService implements UserDetailsService
{
	@Autowired
	private UsersRepository userRepository;

	public CustomUsersDetailsService(UsersRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		return null;
	}
}
