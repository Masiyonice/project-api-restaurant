package com.menuchallenge.challenge08.entity;

import com.menuchallenge.challenge08.constant.DbConnector;
import com.menuchallenge.challenge08.constant.RoleUserOption;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = DbConnector.ROLE)
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	private RoleUserOption role;
}
