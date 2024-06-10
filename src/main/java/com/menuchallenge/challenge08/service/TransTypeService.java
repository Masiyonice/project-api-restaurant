package com.menuchallenge.challenge08.service;

import com.menuchallenge.challenge08.constant.TransTypeOption;
import com.menuchallenge.challenge08.dto.request.TransTypeRequest;
import com.menuchallenge.challenge08.entity.TransType;

import java.util.Optional;

public interface TransTypeService {

	public TransType  createOrGet(TransTypeRequest transTypeRequest);
	public TransType createOrGet(TransTypeOption transType);

}
