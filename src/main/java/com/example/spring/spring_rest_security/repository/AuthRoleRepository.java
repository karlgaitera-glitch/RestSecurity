package com.example.spring.spring_rest_security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring.spring_rest_security.entity.Auth_Role;

public interface AuthRoleRepository extends JpaRepository<Auth_Role, Long> {
    Optional<Auth_Role> findByName(String theUserName);

    Boolean existsByName(String theUserName);
}
