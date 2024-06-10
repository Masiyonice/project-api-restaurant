package com.menuchallenge.challenge08.controller;

import com.menuchallenge.challenge08.constant.ApiUrl;
import com.menuchallenge.challenge08.constant.ResponseMessage;
import com.menuchallenge.challenge08.dto.request.RegisterUserRequest;
import com.menuchallenge.challenge08.dto.responses.CommonResponse;
import com.menuchallenge.challenge08.dto.responses.LoginResponse;
import com.menuchallenge.challenge08.dto.responses.RegisterResponse;
import com.menuchallenge.challenge08.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = ApiUrl.REGISTER_API)
public class AuthController {

	private final AuthService authService;

	@PostMapping(path = "/register")
	public ResponseEntity<CommonResponse<?>> registerUser(@RequestBody RegisterUserRequest request){
		RegisterResponse response = authService.register(request);
		CommonResponse<RegisterResponse> build = CommonResponse.<RegisterResponse>builder()
				.statusCode(HttpStatus.OK.value())
				.message(ResponseMessage.SUCCESS_SAVE_DATA)
				.data(response)
				.build();
		return ResponseEntity.status(HttpStatus.CREATED).body(build);
	}

	@PostMapping(path = "/login")
	public ResponseEntity<CommonResponse<?>> loginUser(@RequestBody RegisterUserRequest request){
		LoginResponse login = authService.login(request);
		CommonResponse<LoginResponse> build = CommonResponse.<LoginResponse>builder()
				.statusCode(HttpStatus.OK.value())
				.message(ResponseMessage.SUCCESS_GET_DATA)
				.data(login)
				.build();
		return ResponseEntity.ok(build);
	}

}