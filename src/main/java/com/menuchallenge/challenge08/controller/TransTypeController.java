package com.menuchallenge.challenge08.controller;

import com.menuchallenge.challenge08.constant.ApiUrl;
import com.menuchallenge.challenge08.constant.ResponseMessage;
import com.menuchallenge.challenge08.dto.request.TransTypeRequest;
import com.menuchallenge.challenge08.dto.responses.CommonResponse;
import com.menuchallenge.challenge08.entity.TransType;
import com.menuchallenge.challenge08.service.TransTypeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(path = ApiUrl.TRANS_TYPE_API)
public class TransTypeController {

	private final TransTypeService transTypeService;

	@PostMapping
	public ResponseEntity<CommonResponse<TransType>> getOrCreate(@RequestBody TransTypeRequest transTypeRequest){
		TransType orGet = transTypeService.createOrGet(transTypeRequest);

		CommonResponse<TransType> build = CommonResponse.<TransType>builder()
				.statusCode(HttpStatus.OK.value())
				.message(ResponseMessage.SUCCESS_GET_DATA)
				.data(orGet)
				.build();

		return ResponseEntity.ok(build);
	}

}
