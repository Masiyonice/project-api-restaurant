package com.menuchallenge.challenge08.service;

import com.menuchallenge.challenge08.dto.request.CustomerRequest;
import com.menuchallenge.challenge08.dto.request.UpdateCustomerRequest;
import com.menuchallenge.challenge08.dto.responses.CustomerResponse;
import com.menuchallenge.challenge08.entity.Customer;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CustService {
    CustomerResponse create(CustomerRequest customerRequest);
    Page<CustomerResponse> getAll(CustomerRequest customerRequest);
    Customer getById(String id);
    CustomerResponse update(UpdateCustomerRequest request);
    Customer createCust(Customer customer);
}
