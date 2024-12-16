package com.usernamewithpasswordandotp.providers;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UsernamePasswordAuthProvider extends DaoAuthenticationProvider {

	private UserDetailsService userDetailsService;
	private PasswordEncoder passwordEncoder;

	public UsernamePasswordAuthProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
		super.setUserDetailsService(userDetailsService);
		super.setPasswordEncoder(passwordEncoder);
		this.userDetailsService = userDetailsService;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String userName = authentication.getName();
		String password = authentication.getCredentials().toString();
		System.out.println("UserName " + userName + " Password " + password);
		UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
		if (userDetails == null) {
			throw new UsernameNotFoundException("User not found: " + userName);
		}
		
		if (!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("Invalid password for user: " + userName);
		}

		return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
}
