package com.menuchallenge.challenge08.dto.request;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuCreateRequest {

	private String menuName;
	private Long price;

}
