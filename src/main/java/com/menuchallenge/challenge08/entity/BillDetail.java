package com.menuchallenge.challenge08.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.menuchallenge.challenge08.constant.DbConnector;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = DbConnector.DETAILTRAN)
public class BillDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;


	@ManyToOne
	@JoinColumn(name = "bill_id")
	@JsonBackReference
	private Bill bill;

	@ManyToOne
	@JoinColumn(name = "menu_price_id")
	private Menu menu;

	@Column(name = "quantity")
	private Integer quantity;
}
