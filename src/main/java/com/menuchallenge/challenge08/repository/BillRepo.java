package com.menuchallenge.challenge08.repository;

import com.menuchallenge.challenge08.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepo extends JpaRepository<Bill, String> {
}
