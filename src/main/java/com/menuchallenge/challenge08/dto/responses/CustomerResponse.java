package com.menuchallenge.challenge08.dto.responses;

import com.menuchallenge.challenge08.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponse {
    private String name;
    private String mobilePhone;
    private Boolean status;

    public CustomerResponse(Customer customer){
        this.name = customer.getCustomerName();
        this.mobilePhone = customer.getMobilePhoneNo();
        this.status = customer.getIsMember();
    }
}
