package com.menuchallenge.challenge08.service.implementation;

import com.menuchallenge.challenge08.dto.request.CustomerRequest;
import com.menuchallenge.challenge08.dto.request.UpdateCustomerRequest;
import com.menuchallenge.challenge08.dto.responses.CustomerResponse;
import com.menuchallenge.challenge08.entity.Customer;
import com.menuchallenge.challenge08.repository.CustRepo;
import com.menuchallenge.challenge08.service.CustService;
import com.menuchallenge.challenge08.specification.CustomerSpecification;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CustomerServiceImplement implements CustService {

    private final CustRepo custRepo;

    @Transactional(rollbackOn = Exception.class)
    @Override
    public CustomerResponse create(CustomerRequest customerRequest) {
        Customer build = Customer.builder()
                .customerName(customerRequest.getName())
                .mobilePhoneNo(customerRequest.getMobilePhone())
                .isMember(customerRequest.getIsMember())
                .build();
        custRepo.saveAndFlush(build);

        return CustomerResponse.builder()
                .name(build.getCustomerName())
                .mobilePhone(build.getMobilePhoneNo())
                .status(build.getIsMember())
                .build();
    }

    @Override
    public Page<CustomerResponse> getAll(CustomerRequest customerRequest) {

        //PAGINATION
        int page;
        if (customerRequest.getPage() == null) {
            page = 1;
        } else if (customerRequest.getPage() < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Page number cannot be less than 1");
        } else {
            page = customerRequest.getPage();
        }
        int size = 2;
        if(customerRequest.getSize() != null){
            size = customerRequest.getSize();
        }
        PageRequest pageRequest = PageRequest.of((page - 1), size);


        //TAKE DATA
        if(customerRequest.getIsMember() == null &&
           customerRequest.getMobilePhone() == null &&
           customerRequest.getName() == null){

                Page<CustomerResponse> all = custRepo.findAll(pageRequest).map(CustomerResponse::new);

                    return all;
        }

        Specification<Customer> input = CustomerSpecification.getdata(customerRequest);

        Page<CustomerResponse> list1 = custRepo.findAll(input,pageRequest).map(CustomerResponse::new);

        return list1;
    }

    @Override
    public Customer getById(String id) {
        return custRepo.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Customer Not Found"));
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public CustomerResponse update(UpdateCustomerRequest customerRequest) {
        Customer byId = getById(customerRequest.getId());

        if(customerRequest.getIsMember() != null){
           byId.setIsMember(customerRequest.getIsMember());
        }

        if(customerRequest.getName() != null){
            byId.setCustomerName(customerRequest.getName());
        }

        if(customerRequest.getMobilePhone() != null){
            byId.setMobilePhoneNo(customerRequest.getMobilePhone());
        }

        custRepo.saveAndFlush(byId);
        return CustomerResponse.builder()
                                .name(byId.getCustomerName())
                                .mobilePhone(byId.getMobilePhoneNo())
                                .status(byId.getIsMember())
                                .build();
    }

    @Override
    public Customer createCust(Customer customer) {
        return custRepo.saveAndFlush(customer);
    }
}
