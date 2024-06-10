package com.menuchallenge.challenge08.controller;

import com.menuchallenge.challenge08.constant.ApiUrl;
import com.menuchallenge.challenge08.constant.ResponseMessage;
import com.menuchallenge.challenge08.dto.request.TableNumberCreateRequest;
import com.menuchallenge.challenge08.dto.responses.CommonResponse;
import com.menuchallenge.challenge08.entity.TableNumberEnt;
import com.menuchallenge.challenge08.service.TableNumberService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(path = ApiUrl.TABLE_NUMBER_API)
public class TableNumberController {

	private final TableNumberService tableNumberService;

	@PostMapping
	public ResponseEntity<CommonResponse<TableNumberEnt>> getOrCreate(@RequestBody TableNumberCreateRequest createRequest){
		TableNumberEnt output = tableNumberService.getOrSaveTable(createRequest);
		CommonResponse<TableNumberEnt> build = CommonResponse.<TableNumberEnt>builder()
				.data(output)
				.statusCode(HttpStatus.OK.value())
				.message(ResponseMessage.SUCCESS_SAVE_DATA)
				.build();
		return ResponseEntity.ok(build);
	}

}
