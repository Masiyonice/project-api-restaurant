package com.menuchallenge.challenge08.service;

import com.menuchallenge.challenge08.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

	User getByUserId(String id);

	User getByContext();
}
