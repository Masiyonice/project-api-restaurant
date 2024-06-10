package com.menuchallenge.challenge08.repository;

import com.menuchallenge.challenge08.constant.TableNumber;
import com.menuchallenge.challenge08.entity.TableNumberEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TableNumberRepo extends JpaRepository<TableNumberEnt, Long> {
		Optional<TableNumberEnt> findByTableNumber(TableNumber tableNumber);

}
