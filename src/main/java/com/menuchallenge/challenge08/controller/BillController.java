package com.menuchallenge.challenge08.controller;

import com.menuchallenge.challenge08.constant.ApiUrl;
import com.menuchallenge.challenge08.constant.ResponseMessage;
import com.menuchallenge.challenge08.dto.request.BillCreateRequest;
import com.menuchallenge.challenge08.dto.responses.BillCreateResponse;
import com.menuchallenge.challenge08.dto.responses.CommonResponse;
import com.menuchallenge.challenge08.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = ApiUrl.BILL_API)
public class BillController {

	private final BillService billService;

	@PostMapping
	ResponseEntity<CommonResponse<BillCreateResponse>> createBill(
			@RequestBody BillCreateRequest createRequest
			){
		BillCreateResponse bill = billService.createBill(createRequest);
		CommonResponse<BillCreateResponse> build = CommonResponse.<BillCreateResponse>builder()
				.statusCode(HttpStatus.OK.value())
				.message(ResponseMessage.SUCCESS_SAVE_DATA)
				.data(bill)
				.build();
		return ResponseEntity.ok(build);
	}

}
