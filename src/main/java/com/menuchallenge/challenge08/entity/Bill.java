package com.menuchallenge.challenge08.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.menuchallenge.challenge08.constant.DbConnector;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = DbConnector.TRANSACTION)
public class Bill {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	@Temporal(TemporalType.DATE)
	@Column(name = "trans_date", updatable = false)
	private Date transDate;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@ManyToOne
	@JoinColumn(name =  "table_id")
	private TableNumberEnt tableNumberEnt;

	@ManyToOne
	@JoinColumn(name = "trans_type_id")
	private TransType transType;

	@Column(name = "total_amount")
	private Long totalBill;

	@OneToMany
	@JsonManagedReference
	private List<BillDetail> billDetails;

}
