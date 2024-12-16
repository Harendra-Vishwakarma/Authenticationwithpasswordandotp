package com.usernamewithpasswordandotp.service;

import java.time.LocalDateTime;

import com.usernamewithpasswordandotp.dto.OtpDto;
import com.usernamewithpasswordandotp.dto.UserDto;
import com.usernamewithpasswordandotp.model.User;

public interface OtpService {
	void insertOtp(OtpDto otpDto,User user);
	boolean isOtpExpired(LocalDateTime expirationTime );
	UserDto findUserByEmail(String email);
	

}
