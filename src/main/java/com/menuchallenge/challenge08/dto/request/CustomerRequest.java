package com.menuchallenge.challenge08.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRequest {
    private String name;
    private String mobilePhone;
    private Boolean isMember;
    private Integer page;
    private Integer size;
}
