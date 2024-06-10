package com.menuchallenge.challenge08.dto.responses;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuResponse {
	private String menuName;
	private Long price;
}
