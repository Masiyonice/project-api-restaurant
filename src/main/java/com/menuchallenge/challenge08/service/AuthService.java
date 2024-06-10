package com.menuchallenge.challenge08.service;

import com.menuchallenge.challenge08.dto.request.RegisterUserRequest;
import com.menuchallenge.challenge08.dto.responses.LoginResponse;
import com.menuchallenge.challenge08.dto.responses.RegisterResponse;

public interface AuthService {

	RegisterResponse register(RegisterUserRequest request);
	RegisterResponse registerAdmin(RegisterUserRequest request);
	LoginResponse login(RegisterUserRequest request);

}
