package com.menuchallenge.challenge08.dto.request;

import com.menuchallenge.challenge08.constant.TableNumber;
import com.menuchallenge.challenge08.constant.TransTypeOption;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BillCreateRequest {

	//FOKUS BUAT DTO BILL CREATE
	private String idCustomer;
	private Long tableNumberId;
	private TransTypeOption transTypeOption;
	private List<BillDetailCreateRequest> billDetailReq;

}
