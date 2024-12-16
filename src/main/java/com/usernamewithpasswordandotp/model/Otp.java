package com.usernamewithpasswordandotp.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "otps") 
public class Otp {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String otpValue;
	private LocalDateTime expirationTime;
	private LocalDateTime createdAt;
	private boolean status;
	private String email;
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
}
