package com.usernamewithpasswordandotp.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.usernamewithpasswordandotp.providers.MobileOtpAuthProvider;
import com.usernamewithpasswordandotp.providers.UsernamePasswordAuthProvider;
import com.usernamewithpasswordandotp.serviceimpl.CustomMobileOtpUserDetailsService;
import com.usernamewithpasswordandotp.serviceimpl.CustomUserPasswordUserDetailsService;

@Configuration
public class SecurityConfig {

	@Autowired
	private CustomUserPasswordUserDetailsService customUserPasswordUserDetailsService;
	@Autowired
	private CustomMobileOtpUserDetailsService customMobileOtpUserDetailsService;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.cors(customizer -> customizer.disable()).csrf(customizer -> customizer.disable())
				.authorizeHttpRequests(
						http -> http.requestMatchers("/auth/**").permitAll().anyRequest().authenticated())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).build();
	}

	@Bean
	AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = http
				.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.authenticationProvider(usernamePasswordAuthProvider())
				.authenticationProvider(mobileOtpAuthProvider());
		return authenticationManagerBuilder.build();
	}

	@Bean
	UsernamePasswordAuthProvider usernamePasswordAuthProvider() {
		return new UsernamePasswordAuthProvider(customUserPasswordUserDetailsService, passwordEncoder());
	}

	@Bean
	MobileOtpAuthProvider mobileOtpAuthProvider() {
		return new MobileOtpAuthProvider(customMobileOtpUserDetailsService, passwordEncoder());
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
