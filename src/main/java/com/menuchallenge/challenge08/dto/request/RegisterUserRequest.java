package com.menuchallenge.challenge08.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterUserRequest {

	private String userName;
	private String password;

}
