package com.menuchallenge.challenge08.entity;


import com.menuchallenge.challenge08.constant.DbConnector;
import com.menuchallenge.challenge08.constant.TableNumber;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = DbConnector.TABLE_NUMBER_DB)
public class TableNumberEnt {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "nt_generator")
	@SequenceGenerator(name = "nt_generator", sequenceName = "nt_sequence", allocationSize = 1)
	private Long id;

	@Column(name = "table_number", unique = true)
	@Enumerated(EnumType.STRING)
	private TableNumber tableNumber;
}
