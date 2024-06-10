package com.menuchallenge.challenge08.service;

import com.menuchallenge.challenge08.dto.request.TableNumberCreateRequest;
import com.menuchallenge.challenge08.entity.TableNumberEnt;

import java.util.List;

public interface TableNumberService {
	TableNumberEnt getOrSaveTable(TableNumberCreateRequest tableNumberCreateRequest);
	List<TableNumberEnt> getAll();
	TableNumberEnt getById(Long id);
	TableNumberEnt update(TableNumberEnt numberTable);
}
