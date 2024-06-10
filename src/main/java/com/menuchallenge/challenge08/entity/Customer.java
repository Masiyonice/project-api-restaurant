package com.menuchallenge.challenge08.entity;

import com.menuchallenge.challenge08.constant.DbConnector;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = DbConnector.CUSTOMER)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)

    @Column(updatable = false, name = "id")
    private String id;

    @Column(name = "customer_name",nullable = false)
    private String customerName;

    @Column(name="mobile_phone_no", unique = true)
    private String mobilePhoneNo;

    @Column(name="is_member")
    private Boolean isMember;

    @OneToOne
    @Column(name = "user_id")
    private User user;
}
