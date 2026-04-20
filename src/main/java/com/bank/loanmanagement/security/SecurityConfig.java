package com.bank.loanmanagement.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private JwtAuthFilter jwtAuthFilter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth -> auth
				// Public endpoints
				.requestMatchers("/api/auth/**").permitAll()
				// Admin only endpoints
				.requestMatchers("/api/admin/**").hasRole("ADMIN")
				// Customer only endpoints
				.requestMatchers("/api/customer/**").hasRole("CUSTOMER")
				// Account endpoints — both Admin & Customer
				.requestMatchers("/api/accounts/**").hasAnyRole("CUSTOMER", "ADMIN")
				// Account endpoints for transcations — both Admin & Customer
				.requestMatchers("/api/transactions/**").hasAnyRole("ADMIN", "CUSTOMER")
				// Account endpoints for loan access — Admin
				.requestMatchers("/api/loans/admin/**").hasRole("ADMIN")
				// Account endpoints for loans — both Admin & Customer
				.requestMatchers("/api/loans/**").hasAnyRole("ADMIN", "CUSTOMER")
				// All other endpoints need authentication
				.anyRequest().authenticated())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
}