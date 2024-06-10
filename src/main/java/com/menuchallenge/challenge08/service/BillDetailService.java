package com.menuchallenge.challenge08.service;

import com.menuchallenge.challenge08.dto.request.BillDetailCreateRequest;
import com.menuchallenge.challenge08.dto.responses.BillDetailResponse;
import com.menuchallenge.challenge08.entity.Bill;
import com.menuchallenge.challenge08.entity.BillDetail;

import java.util.List;

public interface BillDetailService {
	public List<BillDetail> createDetail(List<BillDetailCreateRequest> request, Bill bill);
}
