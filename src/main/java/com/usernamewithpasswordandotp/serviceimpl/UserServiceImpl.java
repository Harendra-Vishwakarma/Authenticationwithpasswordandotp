package com.usernamewithpasswordandotp.serviceimpl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.usernamewithpasswordandotp.dto.UserDto;
import com.usernamewithpasswordandotp.model.User;
import com.usernamewithpasswordandotp.repository.UserRepository;
import com.usernamewithpasswordandotp.service.OtpService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl {

	private final UserRepository userRepository;
	
	private final OtpService otpService;

	private final PasswordEncoder encoder;

	public String registerUser(UserDto userDto) {
		User user = new User();
		user.setUsername(userDto.getUsername());
		user.setPassword(encoder.encode(userDto.getPassword()));
		user.setMobileNumber(userDto.getMobileNumber());
		user.setEmail(userDto.getEmail());
		userRepository.save(user);
		return "success";
	}
	
	public String sendOtp(UserDto userDto) {
		
		User user=userRepository.findByEmail(userDto.getEmail());
		if(user==null) {
			System.out.println("User not found");
			return "Failed";
		}
		otpService.insertOtp(null, user);
		return "success";
		
	}

}
