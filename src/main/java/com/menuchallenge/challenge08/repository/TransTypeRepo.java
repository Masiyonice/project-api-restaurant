package com.menuchallenge.challenge08.repository;

import com.menuchallenge.challenge08.constant.TransTypeOption;
import com.menuchallenge.challenge08.entity.TransType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransTypeRepo extends JpaRepository<TransType, String> {

	Optional<TransType> findByTransTypeOption(TransTypeOption transTypeOption);
}
