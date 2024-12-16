package com.usernamewithpasswordandotp.serviceimpl;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.usernamewithpasswordandotp.dto.UserDto;
import com.usernamewithpasswordandotp.service.OtpService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomMobileOtpUserDetailsService implements UserDetailsService {

	
	private final OtpService otpService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserDto user = otpService.findUserByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("User Not found");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getOtp(),
				new ArrayList<>());
	}
//	private Set<SimpleGrantedAuthority> getAuthority() {
//		final Set<SimpleGrantedAuthority> authorities = new HashSet<>();
//		authorities.add(new SimpleGrantedAuthority("ROLE_PARENT"));
//		return authorities;
//	}
}
