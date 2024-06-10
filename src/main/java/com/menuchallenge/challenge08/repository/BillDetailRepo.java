package com.menuchallenge.challenge08.repository;

import com.menuchallenge.challenge08.entity.BillDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillDetailRepo extends JpaRepository<BillDetail, String> {
}
