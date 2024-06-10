package com.menuchallenge.challenge08.service.implementation;

import com.menuchallenge.challenge08.dto.request.MenuCreateRequest;
import com.menuchallenge.challenge08.dto.request.MenuUpdateRequest;
import com.menuchallenge.challenge08.dto.responses.MenuResponse;
import com.menuchallenge.challenge08.entity.Menu;
import com.menuchallenge.challenge08.repository.MenuRepo;
import com.menuchallenge.challenge08.service.MenuService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class MenuServiceImplement implements MenuService {

	private final MenuRepo menuRepo;

	@Override
	public MenuResponse create(MenuCreateRequest createRequest) {
		menuRepo.saveAndFlush(Menu.builder()
				.menuName(createRequest.getMenuName())
				.price(createRequest.getPrice())
				.build());
		return MenuResponse.builder()
				.menuName(createRequest.getMenuName())
				.price(createRequest.getPrice())
				.build();
	}

	@Override
	public List<MenuResponse> getAll() {
		return menuRepo.findAll().stream().map(
				detail -> MenuResponse.builder()
						.menuName(detail.getMenuName())
						.price(detail.getPrice())
						.build()
		).toList();
	}

	@Override
	public Menu getById(Long id) {
		return menuRepo.findById(id).orElseThrow(
				()-> new ResponseStatusException(HttpStatus.NOT_FOUND
				,"Menu Not Found")
		);
	}

	@Override
	public MenuResponse update(MenuUpdateRequest menuUpdateRequest) {
		Menu byId = getById(menuUpdateRequest.getId());

		if(menuUpdateRequest.getMenuName() != null){
			byId.setMenuName(menuUpdateRequest.getMenuName());
		}

		if(menuUpdateRequest.getPrice() != null){
			byId.setPrice(menuUpdateRequest.getPrice());
		}

		menuRepo.saveAndFlush(byId);
		return MenuResponse.builder()
				.menuName(byId.getMenuName())
				.price(byId.getPrice())
				.build();
	}
}
