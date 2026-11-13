package com.example.spring.spring_rest_security.controller;

import java.util.Collections;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication
        .UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.spring_rest_security.dto.AuthLoginDto;
import com.example.spring.spring_rest_security.dto.AuthRoleRegisterDto;
import com.example.spring.spring_rest_security.dto.AuthUserRegisterDto;
import com.example.spring.spring_rest_security.entity.Auth_Role;
import com.example.spring.spring_rest_security.entity.Auth_User;
import com.example.spring.spring_rest_security.repository.AuthRoleRepository;
import com.example.spring.spring_rest_security.repository.AuthUserRepository;

/**
 * REST controller for managing authentication and registration endpoints.
 * Provides APIs to register new roles, register new users, and authenticate
 * existing users.
 */
@RestController
@RequestMapping("api/auth")
public class AuthenticationController {

	private AuthenticationManager authenticationManager;
	private AuthUserRepository authUserRepository;
	private AuthRoleRepository authRoleRepository;
	private BCryptPasswordEncoder passwordEncoder;

	public AuthenticationController(
			AuthenticationManager authenticationManager,
			AuthUserRepository authUserRepository,
			AuthRoleRepository authRoleRepository,
			BCryptPasswordEncoder passwordEncoder) {
		this.authenticationManager = authenticationManager;
		this.authUserRepository = authUserRepository;
		this.authRoleRepository = authRoleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	/**
     * Registers a new authorization role in the system.
     *
     * @param authRoleRegisterDto DTO containing the name of the role to create
     * @return a {@link ResponseEntity} with a success message and HTTP 200 OK,
     *         or an error message with HTTP 400 Bad Request if the role exists
     */
	@PostMapping("/register_authorized_role")
	public ResponseEntity<String> register_authRole(
			@RequestBody AuthRoleRegisterDto authRoleRegisterDto) {
		if (authRoleRepository.existsByName(authRoleRegisterDto.getName())) {
			return new ResponseEntity<>(authRoleRegisterDto.getName()
					+ " already created.", HttpStatus.BAD_REQUEST);
		}

		Auth_Role auth_role = new Auth_Role();
		auth_role.setName(authRoleRegisterDto.getName());

		authRoleRepository.save(auth_role);

		return new ResponseEntity<>(auth_role.getName()
				+ " is successfully created", HttpStatus.OK);
	}

	/**
     * Registers a new user and assigns them the default "USER" role.
     *
     * @param authUserRegisterDto DTO containing the new user's username and
     *                            raw password
     * @return a {@link ResponseEntity} with a success message and HTTP 200 OK,
     *         or an error message with HTTP 400 Bad Request if username is taken
     * @throws RuntimeException if the default "USER" role is not found in the
     *                          database
     */
	@PostMapping("/register_authorized_user")
	public ResponseEntity<String> register_authUser(
			@RequestBody AuthUserRegisterDto authUserRegisterDto) {
		if (authUserRepository.existsByUsername(
				authUserRegisterDto.getUsername())) {
			return new ResponseEntity<>(authUserRegisterDto.getUsername()
					+ " is already taken.", HttpStatus.BAD_REQUEST);
		}

		Auth_User auth_user = new Auth_User();
		auth_user.setUsername(authUserRegisterDto.getUsername());
		auth_user.setPassword(passwordEncoder.encode(
				authUserRegisterDto.getPassword()));

		Auth_Role auth_role = authRoleRepository.findByName("USER")
				.orElseThrow(() -> new RuntimeException(
						"Roles USER not found"));
		auth_user.setRoles(Collections.singletonList(auth_role));

		authUserRepository.save(auth_user);

		return new ResponseEntity<>(auth_user.getUsername()
				+ " is succesfully registered", HttpStatus.OK);
	}

	/**
     * Authenticates a user with the provided login credentials and sets the
     * security context.
     *
     * @param authLoginDto DTO containing the user's login username and password
     * @return a {@link ResponseEntity} confirming successful sign-in with
     *         HTTP 200 OK
     */
	@PostMapping("/auth_login")
	public ResponseEntity<String> login(
			@RequestBody AuthLoginDto authLoginDto) {
		Authentication authenticate = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						authLoginDto.getUsername(),
						authLoginDto.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authenticate);
		return new ResponseEntity<>("Sign in successfully!", HttpStatus.OK);
	}
}
