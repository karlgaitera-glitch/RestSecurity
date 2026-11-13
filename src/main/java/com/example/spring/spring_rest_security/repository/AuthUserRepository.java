package com.example.spring.spring_rest_security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring.spring_rest_security.entity.Auth_User;

public interface AuthUserRepository extends JpaRepository<Auth_User, Long> {
    Optional<Auth_User> findByUsername(String theUserName);

    Boolean existsByUsername(String theUserName);
}
