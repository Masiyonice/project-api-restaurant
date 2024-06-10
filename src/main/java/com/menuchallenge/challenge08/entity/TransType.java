package com.menuchallenge.challenge08.entity;

import com.menuchallenge.challenge08.constant.DbConnector;
import com.menuchallenge.challenge08.constant.TransTypeOption;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = DbConnector.TRANS_TYPE)
public class TransType {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	@Column(name = "description")
	@Enumerated(EnumType.STRING)
	private TransTypeOption transTypeOption;
}
