package com.menuchallenge.challenge08.service.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.menuchallenge.challenge08.constant.ResponseMessage;
import com.menuchallenge.challenge08.constant.TableNumber;
import com.menuchallenge.challenge08.dto.request.TableNumberCreateRequest;
import com.menuchallenge.challenge08.entity.TableNumberEnt;
import com.menuchallenge.challenge08.repository.TableNumberRepo;
import com.menuchallenge.challenge08.service.TableNumberService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class TableNumberServiceImplementation implements TableNumberService {

	private final TableNumberRepo tableRepo;

	@Transactional(rollbackOn = Exception.class)
	@Override
	public TableNumberEnt getOrSaveTable (TableNumberCreateRequest tableNumberCreateRequest) {

		//validation

		return tableRepo.findByTableNumber(tableNumberCreateRequest.getTableNumber()).orElseGet(
				() -> tableRepo.saveAndFlush(
						TableNumberEnt.builder()
								.tableNumber(tableNumberCreateRequest.getTableNumber())
								.build()
				)
		);
	}

	@Override
	public List<TableNumberEnt> getAll() {
		List<TableNumberEnt> all = tableRepo.findAll();
		return all;
	}

	@Override
	public TableNumberEnt getById(Long id) {
		return tableRepo.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ResponseMessage.ERROR_NOT_FOUND)
		);
	}

	@Override
	public TableNumberEnt update(TableNumberEnt numberTable) {
		getById(numberTable.getId());
		return tableRepo.saveAndFlush(numberTable);
	}

}
