package com.menuchallenge.challenge08.dto.responses;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwTClaims {

	private String userAccountId;
	private List<String> roles;
}
