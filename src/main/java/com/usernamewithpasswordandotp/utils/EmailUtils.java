package com.usernamewithpasswordandotp.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
@Component
public class EmailUtils {
	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String fromEmail;

	@Async("myTaskExecutor")
	public 	void sendEmail(String email, String subject, String content) {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			helper.setFrom(new InternetAddress(fromEmail) );
			helper.setTo(email);
			helper.setSubject(subject);
			helper.setText(content, true);
			javaMailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	@Async("myTaskExecutor")
	public void sendBuilkEmail(String[] emails, String subject, String content) {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			helper.setFrom(fromEmail);
			helper.setTo(emails);
			helper.setSubject(subject);
			helper.setText(content, true);
			javaMailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
