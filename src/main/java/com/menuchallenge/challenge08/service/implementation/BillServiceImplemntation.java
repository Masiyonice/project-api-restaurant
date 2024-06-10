package com.menuchallenge.challenge08.service.implementation;

import com.menuchallenge.challenge08.dto.request.BillCreateRequest;
import com.menuchallenge.challenge08.dto.responses.BillCreateResponse;
import com.menuchallenge.challenge08.dto.responses.BillDetailResponse;
import com.menuchallenge.challenge08.entity.*;
import com.menuchallenge.challenge08.repository.BillRepo;
import com.menuchallenge.challenge08.service.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class BillServiceImplemntation implements BillService {

	private final BillRepo billRepo;
	private final BillDetailService billDetailService;
	private final CustService custService;
	private final TableNumberService tableNumberService;
	private final TransTypeService transTypeService;

	@Transactional(rollbackOn = Exception.class)
	@Override
	public BillCreateResponse createBill(BillCreateRequest createRequest) {

		Customer customerId = custService.getById(createRequest.getIdCustomer());
		TableNumberEnt tableNum = tableNumberService.getById(createRequest.getTableNumberId());
		TransType transType = transTypeService.createOrGet(createRequest.getTransTypeOption());

		Bill data1 = Bill.builder()
				.customer(customerId)
				.transType(transType)
				.transDate(new Date())
				.tableNumberEnt(tableNum)
				.build();

		billRepo.saveAndFlush(data1);

		List<BillDetail> detailData = billDetailService.createDetail(createRequest.getBillDetailReq(), data1);
		data1.setBillDetails(detailData);

		Long totalAmountBill = detailData.stream().mapToLong(
				detail -> calculationTotalPrice(detail.getQuantity(), detail.getMenu().getPrice())
		).sum();

		data1.setTotalBill(totalAmountBill);

		List<BillDetailResponse> billDetailResponses = detailData.stream().map(
				detail -> BillDetailResponse.builder()
						.menuName(detail.getMenu().getMenuName())
						.quantity(detail.getQuantity())
						.totalPrice(calculationTotalPrice(detail.getQuantity(), detail.getMenu().getPrice()))
						.build()
		).toList();

		return BillCreateResponse.builder()
				.nameCustomer(data1.getCustomer().getCustomerName())
				.dateTransaction(data1.getTransDate())
				.tableNumber(data1.getTableNumberEnt().getTableNumber().getTableOption())
				.transTypeOption(data1.getTransType().getTransTypeOption())
				.totalPrice(data1.getTotalBill())
				.detailResponseList(billDetailResponses)
				.build();

	}

	private Long calculationTotalPrice(Integer qty, Long price){
		Long test = (long) qty;
		return test * price;
	}
}
