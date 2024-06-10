package com.menuchallenge.challenge08.specification;

import com.menuchallenge.challenge08.dto.request.CustomerRequest;
import com.menuchallenge.challenge08.entity.Customer;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class CustomerSpecification {

    public static Specification<Customer> getdata(CustomerRequest customerRequest){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(customerRequest.getName() != null){
                Predicate customerName = criteriaBuilder.like(criteriaBuilder.lower(root.get("customerName")), "%" + customerRequest.getName().toLowerCase() + "%");
                predicates.add(customerName);
            }

            if(customerRequest.getMobilePhone() != null){
                Predicate mobilePhoneNo = criteriaBuilder.equal(root.get("mobilePhoneNo"), customerRequest.getMobilePhone());
                predicates.add(mobilePhoneNo);
            }

            if(customerRequest.getIsMember() != null){
                Predicate isMember = criteriaBuilder.equal(root.get("isMember"), customerRequest.getIsMember());
                predicates.add(isMember);
            }

            return query.where(criteriaBuilder.or(predicates.toArray(new Predicate[]{}))).getRestriction();
        };
    }
}
