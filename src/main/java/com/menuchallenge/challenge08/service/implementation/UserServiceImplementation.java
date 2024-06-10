package com.menuchallenge.challenge08.service.implementation;

import com.menuchallenge.challenge08.entity.User;
import com.menuchallenge.challenge08.repository.UserRepo;
import com.menuchallenge.challenge08.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {

	private final UserRepo userRepo;

	@Transactional(readOnly = true)
	@Override
	public User getByUserId(String id) {
		return userRepo.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found")
		);
	}

	@Override
	public User getByContext() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return userRepo.findByUsername(authentication.getPrincipal().toString()).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found from context")
		);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepo.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException("username not found")
		);
	}
}
