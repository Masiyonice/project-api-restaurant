package com.menuchallenge.challenge08.service;

import com.menuchallenge.challenge08.dto.request.BillCreateRequest;
import com.menuchallenge.challenge08.dto.responses.BillCreateResponse;
import com.menuchallenge.challenge08.dto.responses.BillDetailResponse;

public interface BillService {
	BillCreateResponse createBill(BillCreateRequest createRequest);
}
