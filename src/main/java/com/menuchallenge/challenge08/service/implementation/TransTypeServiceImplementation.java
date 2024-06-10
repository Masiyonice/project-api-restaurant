package com.menuchallenge.challenge08.service.implementation;

import com.menuchallenge.challenge08.constant.TransTypeOption;
import com.menuchallenge.challenge08.dto.request.TransTypeRequest;
import com.menuchallenge.challenge08.entity.TransType;
import com.menuchallenge.challenge08.repository.TransTypeRepo;
import com.menuchallenge.challenge08.service.TransTypeService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TransTypeServiceImplementation implements TransTypeService {

	private final TransTypeRepo transTypeRepo;

	@Transactional(rollbackOn = Exception.class)
	@Override
	public TransType createOrGet(TransTypeRequest request) {

		return transTypeRepo.findByTransTypeOption(request.getTransTypeOption()).orElseGet(
				() -> transTypeRepo.saveAndFlush(
						TransType.builder()
								.transTypeOption(request.getTransTypeOption())
								.build()
				)
		);
	}

	@Override
	public TransType createOrGet(TransTypeOption transType) {
		return transTypeRepo.findByTransTypeOption(transType).orElseGet(
				() -> transTypeRepo.saveAndFlush(
						TransType.builder()
								.transTypeOption(transType)
								.build()
				)
		);
	}
}
