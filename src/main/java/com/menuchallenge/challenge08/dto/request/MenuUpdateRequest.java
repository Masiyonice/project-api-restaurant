package com.menuchallenge.challenge08.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuUpdateRequest {

	private Long id;
	private String menuName;
	private Long price;

}
