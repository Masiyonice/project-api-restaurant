package com.menuchallenge.challenge08.service.implementation;


import com.menuchallenge.challenge08.dto.request.BillDetailCreateRequest;
import com.menuchallenge.challenge08.dto.responses.BillDetailResponse;
import com.menuchallenge.challenge08.entity.Bill;
import com.menuchallenge.challenge08.entity.BillDetail;
import com.menuchallenge.challenge08.repository.BillDetailRepo;
import com.menuchallenge.challenge08.service.BillDetailService;
import com.menuchallenge.challenge08.service.MenuService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class BillDetailServiceImplementation implements BillDetailService {

	private final BillDetailRepo billDetailRepo;
	private final MenuService menuService;

	@Transactional(rollbackOn = Exception.class)
	@Override
	public List<BillDetail> createDetail(List<BillDetailCreateRequest> request, Bill bill) {
		List<BillDetail> listSave = request.stream().map(
				detail -> BillDetail.builder()
						.quantity(detail.getQuantity())
						.menu(menuService.getById(detail.getIdMenu()))
						.bill(bill)
						.build()
		).toList();
		return  billDetailRepo.saveAllAndFlush(listSave);
	}

}
