package com.example.demo.service;

import static java.util.Collections.emptyList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.domain.UserApplication;

@Service
public class UserApplicationDetailsServiceImpl implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		if (username == null) {
			throw new UsernameNotFoundException(username);
		}

		if (username.isBlank() == true) {
			throw new UsernameNotFoundException(username);
		}

		if (username.equals("admin") == false) {
			throw new UsernameNotFoundException(username);
		}

		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		UserApplication userApplication = new UserApplication(username, bCryptPasswordEncoder.encode("password"));

		return new User(userApplication.getUsername(), userApplication.getPassword(), emptyList());
	}

}
