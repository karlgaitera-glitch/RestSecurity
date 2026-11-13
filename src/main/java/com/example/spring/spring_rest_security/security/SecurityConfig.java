package com.example.spring.spring_rest_security.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.spring.spring_rest_security.service.CustomUserDetailsService;

/**
 * Spring Security configuration class defining HTTP security rules,
 * password encoding, and authentication management beans.
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.setUserDetailsService(userDetailsService);
    }

    /**
     * Configures the security filter chain for incoming HTTP requests.
     * Disables CSRF, permits unauthenticated access to {@code /api/auth/**},
     * requires authentication for all other endpoints, and enables HTTP Basic.
     *
     * @param security the {@link HttpSecurity} builder to configure
     * @return the configured {@link SecurityFilterChain}
     * @throws Exception if an error occurs during security configuration
     */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity security)
            throws Exception {
        return security
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    /**
     * Provides a BCrypt password encoder bean for hashing passwords.
     *
     * @return a {@link BCryptPasswordEncoder} instance
     */
    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Exposes the {@link AuthenticationManager} bean from the Spring Security
     * configuration.
     *
     * @param authentication the {@link AuthenticationConfiguration} to retrieve
     *                       the manager from
     * @return the configured {@link AuthenticationManager}
     * @throws Exception if the authentication manager cannot be retrieved
     */
    @Bean
    AuthenticationManager authenticationManager(
            AuthenticationConfiguration authentication) throws Exception {
        return authentication.getAuthenticationManager();
    }

    /**
     * Retrieves the configured custom user details service.
     *
     * @return the {@link CustomUserDetailsService} instance
     */
    public CustomUserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    /**
     * Sets the custom user details service.
     *
     * @param userDetailsService the {@link CustomUserDetailsService} to set
     */
    public void setUserDetailsService(
            CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
