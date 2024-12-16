package com.usernamewithpasswordandotp.providers;

import java.util.Collections;
import java.util.Objects;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MobileOtpAuthProvider extends DaoAuthenticationProvider {

	private UserDetailsService userDetailsService;

	public MobileOtpAuthProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
		super.setUserDetailsService(userDetailsService);
		super.setPasswordEncoder(passwordEncoder);
		this.userDetailsService = userDetailsService;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String email = authentication.getName();
		String otp = authentication.getCredentials().toString();
		System.out.println("mobileNo " + email + " otp " + otp);
		UserDetails userDetails = userDetailsService.loadUserByUsername(email);
		if (userDetails == null) {
			throw new UsernameNotFoundException("User not found with mobile number: " + email);
		}
		if (!Objects.equals(otp, userDetails.getPassword())) {
			throw new BadCredentialsException("Invalid password for user: " + email);

		}
		return new UsernamePasswordAuthenticationToken(email, otp, Collections.emptyList());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}