package com.menuchallenge.challenge08.service;

import com.menuchallenge.challenge08.constant.RoleUserOption;
import com.menuchallenge.challenge08.entity.Role;

public interface RoleService {
	Role getOrSave(RoleUserOption roleUserOption);
}
