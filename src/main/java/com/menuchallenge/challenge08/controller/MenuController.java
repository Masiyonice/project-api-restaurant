package com.menuchallenge.challenge08.controller;


import com.menuchallenge.challenge08.constant.ApiUrl;
import com.menuchallenge.challenge08.constant.ResponseMessage;
import com.menuchallenge.challenge08.dto.request.MenuCreateRequest;
import com.menuchallenge.challenge08.dto.request.MenuUpdateRequest;
import com.menuchallenge.challenge08.dto.responses.CommonResponse;
import com.menuchallenge.challenge08.dto.responses.MenuResponse;
import com.menuchallenge.challenge08.entity.Menu;
import com.menuchallenge.challenge08.service.implementation.MenuServiceImplement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = ApiUrl.MENU_API)
public class MenuController {

	private final MenuServiceImplement menuServiceImplement;

	@GetMapping
	public ResponseEntity<CommonResponse<List<MenuResponse>>> getAllMenu(){
		List<MenuResponse> all = menuServiceImplement.getAll();
		CommonResponse<List<MenuResponse>> build = CommonResponse.<List<MenuResponse>>builder()
				.statusCode(HttpStatus.OK.value())
				.message(ResponseMessage.SUCCESS_GET_DATA)
				.data(all)
				.build();
		return ResponseEntity.ok(build);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<CommonResponse<Menu>> getById(@PathVariable Long id){
		Menu byId = menuServiceImplement.getById(id);
		CommonResponse<Menu> build = CommonResponse.<Menu>builder()
				.statusCode(HttpStatus.OK.value())
				.message(ResponseMessage.SUCCESS_GET_DATA)
				.data(byId)
				.build();
		return ResponseEntity.ok(build);
	}

	@PutMapping
	public ResponseEntity<CommonResponse<MenuResponse>> updateMenu(@RequestBody MenuUpdateRequest menuUpdateRequest){
		MenuResponse update = menuServiceImplement.update(menuUpdateRequest);
		CommonResponse<MenuResponse> build = CommonResponse.<MenuResponse>builder()
				.statusCode(HttpStatus.OK.value())
				.message(ResponseMessage.SUCCESS_UPDATE_DATA)
				.data(update)
				.build();
		return ResponseEntity.ok(build);
	}

	@PostMapping
	public ResponseEntity<CommonResponse<MenuResponse>> createMenu(@RequestBody MenuCreateRequest createRequest){
		MenuResponse menuResponse = menuServiceImplement.create(createRequest);
		CommonResponse<MenuResponse> build = CommonResponse.<MenuResponse>builder()
				.statusCode(HttpStatus.OK.value())
				.message(ResponseMessage.SUCCESS_SAVE_DATA)
				.data(menuResponse)
				.build();
		return ResponseEntity.ok(build);
	}
}
