package com.menuchallenge.challenge08.dto.responses;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BillDetailResponse {

	String menuName;
	Integer quantity;
	Long totalPrice;

}
