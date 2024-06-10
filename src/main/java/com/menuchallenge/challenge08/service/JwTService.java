package com.menuchallenge.challenge08.service;

import com.menuchallenge.challenge08.dto.responses.JwTClaims;
import com.menuchallenge.challenge08.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface JwTService {
	String generateToken(User user);
	boolean verifyJwtTokem(String token);
	JwTClaims getClaimsByToken(String token);
}
