package com.usernamewithpasswordandotp.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OtpDto {
	private Long id;
	private String otpValue;
	private LocalDateTime expirationTime;
	private LocalDateTime createdAt;
	private boolean status;
	private String email;
	private Long userId;
}
