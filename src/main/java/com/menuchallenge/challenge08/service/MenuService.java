package com.menuchallenge.challenge08.service;

import com.menuchallenge.challenge08.dto.request.MenuCreateRequest;
import com.menuchallenge.challenge08.dto.request.MenuUpdateRequest;
import com.menuchallenge.challenge08.dto.responses.MenuResponse;
import com.menuchallenge.challenge08.entity.Menu;

import java.util.List;

public interface MenuService {

	MenuResponse create(MenuCreateRequest createRequest);
	List<MenuResponse> getAll();
	Menu getById(Long id);
	MenuResponse update(MenuUpdateRequest menuUpdateRequest);


}
