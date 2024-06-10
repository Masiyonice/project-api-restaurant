package com.menuchallenge.challenge08.dto.responses;
import com.menuchallenge.challenge08.constant.TableNumber;
import com.menuchallenge.challenge08.constant.TransTypeOption;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BillCreateResponse {

	private String nameCustomer;
	private Date dateTransaction;
	private TransTypeOption transTypeOption;
	private String tableNumber;
	private Long totalPrice;
	private List<BillDetailResponse> detailResponseList;

}
