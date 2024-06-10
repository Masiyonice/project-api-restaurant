package com.menuchallenge.challenge08.dto.request;

import com.menuchallenge.challenge08.entity.Bill;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BillDetailCreateRequest {

	private Long idMenu;
	private Integer quantity;
}
