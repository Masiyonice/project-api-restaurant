package com.menuchallenge.challenge08.controller;

import com.menuchallenge.challenge08.constant.ApiUrl;
import com.menuchallenge.challenge08.constant.ResponseMessage;
import com.menuchallenge.challenge08.dto.request.CustomerRequest;
import com.menuchallenge.challenge08.dto.request.UpdateCustomerRequest;
import com.menuchallenge.challenge08.dto.responses.CommonResponse;
import com.menuchallenge.challenge08.dto.responses.CustomerResponse;
import com.menuchallenge.challenge08.dto.responses.PagingResponse;
import com.menuchallenge.challenge08.entity.Customer;
import com.menuchallenge.challenge08.service.CustService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = ApiUrl.CUSTOMER_API)
public class CustomerController {

    private final CustService custService;

    @PostMapping
    ResponseEntity<CommonResponse<CustomerResponse>> customerResponseEntity(@RequestBody CustomerRequest customerRequest){
        CustomerResponse customer = custService.create(customerRequest);
        CommonResponse<CustomerResponse> build = CommonResponse.<CustomerResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_SAVE_DATA)
                .data(customer)
                .build();
        return ResponseEntity.ok(build);
    }

    @PutMapping
    ResponseEntity<CommonResponse<CustomerResponse>> updateCustomer(@RequestBody UpdateCustomerRequest updateCustomerRequest){
        CustomerResponse update = custService.update(updateCustomerRequest);
        CommonResponse<CustomerResponse> build = CommonResponse.<CustomerResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_UPDATE_DATA)
                .data(update)
                .build();
        return ResponseEntity.ok(build);
    }

    @GetMapping
    ResponseEntity<CommonResponse<List<CustomerResponse>>> getAllCustomer(
            @RequestParam(name = "nameCustomer", required = false) String nameCustomer,
            @RequestParam(name = "mobilePhone", required = false) String mobilePhone,
            @RequestParam(name= "isMember", required = false) Boolean isMember,
            @RequestParam(name= "page", required = false) Integer page,
            @RequestParam(name= "size", required = false) Integer size
    ){

        CustomerRequest customerRequest = CustomerRequest.builder()
                .name(nameCustomer)
                .mobilePhone(mobilePhone)
                .isMember(isMember)
                .page(page)
                .size(size)
                .build();

        Page<CustomerResponse> all = custService.getAll(customerRequest);

        PagingResponse build = PagingResponse.builder()
                .totalPages(all.getTotalPages())
                .totalElements(all.getTotalElements())
                .page(all.getPageable().getPageNumber()+1)
                .size(all.getPageable().getPageSize())
                .hasNext(all.hasNext())
                .hasPrevious(all.hasPrevious())
                .build();
        CommonResponse<List<CustomerResponse>> response = CommonResponse.<List<CustomerResponse>>builder()
                                                                        .statusCode(HttpStatus.OK.value())
                                                                        .message(ResponseMessage.SUCCESS_GET_DATA)
                                                                        .data(all.getContent())
                                                                        .pagingResponse(build)
                                                                        .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    ResponseEntity<CommonResponse<Customer>> getCustomerById(@PathVariable String id){
        Customer byId = custService.getById(id);
        CommonResponse<Customer> build = CommonResponse.<Customer>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_GET_DATA)
                .data(byId)
                .build();
        return ResponseEntity.ok(build);
    }
}
