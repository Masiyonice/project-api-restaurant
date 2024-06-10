package com.menuchallenge.challenge08.repository;

import com.menuchallenge.challenge08.constant.RoleUserOption;
import com.menuchallenge.challenge08.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, String> {
	Optional<Role> findByRole(RoleUserOption roleUserOption);
}
