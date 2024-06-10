package com.menuchallenge.challenge08.service.implementation;

import com.menuchallenge.challenge08.constant.RoleUserOption;
import com.menuchallenge.challenge08.dto.request.CustomerRequest;
import com.menuchallenge.challenge08.dto.request.RegisterUserRequest;
import com.menuchallenge.challenge08.dto.responses.CustomerResponse;
import com.menuchallenge.challenge08.dto.responses.LoginResponse;
import com.menuchallenge.challenge08.dto.responses.RegisterResponse;
import com.menuchallenge.challenge08.entity.Customer;
import com.menuchallenge.challenge08.entity.Role;
import com.menuchallenge.challenge08.entity.User;
import com.menuchallenge.challenge08.repository.UserRepo;
import com.menuchallenge.challenge08.service.AuthService;
import com.menuchallenge.challenge08.service.CustService;
import com.menuchallenge.challenge08.service.JwTService;
import com.menuchallenge.challenge08.service.RoleService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImplementation implements AuthService {

	private final UserRepo userRepo;
	private final RoleService roleService;
	private final PasswordEncoder passwordEncoder;
	private final CustService custService;
	private final JwTService jwTService;
	private final AuthenticationManager authenticationManager;

	@Value("${challeng_08.superadmin.username}")
	private String superAdminUsername;

	@Value("${challenge_08.superadmin.password}")
	private String superAdminPassword;

	@Transactional(rollbackFor = Exception.class)
	@PostConstruct
	public void initSuperAdmin(){
		Optional<User> currentUser = userRepo.findByUsername(superAdminUsername);
		if(currentUser.isPresent()){
			return;
		}

		Role superAdmin = roleService.getOrSave(RoleUserOption.SUPER_ADMIN);
		Role admin = roleService.getOrSave(RoleUserOption.ADMIN);
		Role customer = roleService.getOrSave(RoleUserOption.USER);

		User user = User.builder()
				.userName(superAdminUsername)
				.password(passwordEncoder.encode(superAdminPassword))
				.role(List.of(superAdmin, admin, customer))
				.isEnable(true)
				.build();
		userRepo.saveAndFlush(user);
	}


	@Transactional(rollbackFor = Exception.class)
	@Override
	public RegisterResponse register(RegisterUserRequest request) {
		Role role = roleService.getOrSave(RoleUserOption.USER);
		String hashPassword = passwordEncoder.encode(request.getPassword());

		User account = User.builder()
				.userName(request.getUserName())
				.password(hashPassword)
				.role(List.of(role))
				.isEnable(true)
				.build();
		userRepo.saveAndFlush(account);
		Customer customer = Customer.builder()
				.isMember(true)
				.user(account)
				.build();
		custService.createCust(customer);
		return RegisterResponse.builder()
				.username(account.getUsername())
				.roles(account.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
				.build();
	}

	@Transactional(readOnly = true)
	@Override
	public RegisterResponse registerAdmin(RegisterUserRequest request) {
		Role admin = roleService.getOrSave(RoleUserOption.ADMIN);
		Role customer = roleService.getOrSave(RoleUserOption.USER);
		return null;
	}

	@Transactional(readOnly = true)
	@Override
	public LoginResponse login(RegisterUserRequest request) {
		Authentication authentication = new UsernamePasswordAuthenticationToken(
				request.getUserName(),
				request.getPassword()
		);
		Authentication authenticated = authenticationManager.authenticate(authentication);
		User account = (User) authenticated.getPrincipal();
		String token = jwTService.generateToken(account);
		return LoginResponse.builder()
				.username(account.getUsername())
				.token(token)
				.roles(account.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
				.build();
	}
}
