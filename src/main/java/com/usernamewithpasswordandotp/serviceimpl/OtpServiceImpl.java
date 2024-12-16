package com.usernamewithpasswordandotp.serviceimpl;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

import com.usernamewithpasswordandotp.constant.EmailTemplate;
import com.usernamewithpasswordandotp.dto.OtpDto;
import com.usernamewithpasswordandotp.dto.UserDto;
import com.usernamewithpasswordandotp.model.Otp;
import com.usernamewithpasswordandotp.model.User;
import com.usernamewithpasswordandotp.repository.OtpRepository;
import com.usernamewithpasswordandotp.service.OtpService;
import com.usernamewithpasswordandotp.utils.EmailUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OtpServiceImpl implements OtpService {

	private final OtpRepository otpRepository;

	private final EmailUtils emailUtils;

	@Override
	public void insertOtp(OtpDto otpDto, User user) {
		sendOTP(user);
	}

	@Override
	public boolean isOtpExpired(LocalDateTime expirationTime) {
		return LocalDateTime.now().isAfter(expirationTime);
	}

	@Override
	public UserDto findUserByEmail(String email) {

		 List<Otp> otps = otpRepository.findByOtpByEmail(email, LocalDateTime.now());
		if (otps.isEmpty()) {
			throw new RuntimeException("Invalid Otp");
		}
		if (otps != null && !otps.isEmpty()) {
			final Otp otp = otps.get(0);
			if (!otp.getUser().isVerify()) {
				throw new RuntimeException("User not verified");
			}
			final UserDto userDto = new UserDto();
			userDto.setEmail(email);
			userDto.setOtp(otp.getOtpValue());
			return userDto;
		}
		return null;
	}

	private Otp sendOTP(User user) {
		final String generatedOtp = generateOTP(4);
		final Otp otp = new Otp();
		String content = String.format(EmailTemplate.OtpTemplates, generatedOtp);
		emailUtils.sendEmail(user.getEmail(), EmailTemplate.OtpSubject, content);
		otp.setOtpValue(generatedOtp);
		otp.setCreatedAt(LocalDateTime.now());
		otp.setExpirationTime(LocalDateTime.now().plusMinutes(5));
		otp.setEmail(user.getEmail());
		otp.setStatus(false);
		if (user.getId() != null) {
			otp.setUser(user);
		}

		otpRepository.save(otp);
		return otp;
	}

	public String generateOTP(int length) {
		final StringBuilder generatedToken = new StringBuilder();
		final SecureRandom randomNumber = new SecureRandom();
		for (int i = 0; i < length; i++) {
			generatedToken.append(randomNumber.nextInt(9));
		}

		return generatedToken.toString();
	}
}
