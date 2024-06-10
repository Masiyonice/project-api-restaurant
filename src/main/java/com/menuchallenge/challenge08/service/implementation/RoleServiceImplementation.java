package com.menuchallenge.challenge08.service.implementation;

import com.menuchallenge.challenge08.constant.RoleUserOption;
import com.menuchallenge.challenge08.entity.Role;
import com.menuchallenge.challenge08.repository.RoleRepository;
import com.menuchallenge.challenge08.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImplementation implements RoleService {

	private final RoleRepository roleRepository;

	@Override
	public Role getOrSave(RoleUserOption roleUserOption) {
		return roleRepository.findByRole(roleUserOption)
				.orElseGet(() -> roleRepository.saveAndFlush(
						Role.builder()
								.role(roleUserOption)
								.build()
				));
	}
}
