package com.example.spring.spring_rest_security.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.spring.spring_rest_security.entity.Auth_Role;
import com.example.spring.spring_rest_security.entity.Auth_User;
import com.example.spring.spring_rest_security.repository.AuthUserRepository;

/**
 * Custom implementation of Spring Security's {@link UserDetailsService}.
 * Retrieves user credentials and roles from the persistence layer for
 * authentication and authorization.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AuthUserRepository authUserRepository;

    public CustomUserDetailsService(AuthUserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
    }

    /**
     * Locates the user based on the provided username and constructs a
     * Spring Security {@link UserDetails} object.
     *
     * @param theUserName the username identifying the user whose data is
     *                    required
     * @return a fully populated {@link UserDetails} instance
     * @throws UsernameNotFoundException if the user could not be found
     */
    public UserDetails loadUserByUsername(String theUserName) {
        Auth_User user = authUserRepository.findByUsername(theUserName)
                .orElseThrow(
                        () -> new UsernameNotFoundException("Username " +
                                theUserName + "does not exist"));
        return new User(user.getUsername(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    /**
     * Maps a list of {@link Auth_Role} entities into a collection of
     * Spring Security {@link GrantedAuthority} objects.
     *
     * @param roles the list of roles assigned to the user
     * @return a collection of granted authorities
     */
    private Collection<GrantedAuthority> mapRolesToAuthorities(
            List<Auth_Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(
                role.getName()))
                .collect(Collectors.toList());
    }

}
