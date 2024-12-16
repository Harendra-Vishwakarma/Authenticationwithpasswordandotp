package com.usernamewithpasswordandotp.constant;

public class EmailTemplate {
	public static final String OtpTemplates = """

			  <p>Dear [User's Name],</p>
			  <p>We hope this message finds you well!</p>
			  <p>As part of our commitment to security, we have generated a One-Time Password (OTP) for you. This OTP is required to verify your identity and ensure secure access to your account.</p>
			  <p><strong>Your OTP is:</strong> <strong>%s</strong></p>
			  <p>Please note:</p>
			  <ul>
			      <li>This OTP is valid for a limited time only.</li>
			      <li>Do not share this OTP with anyone.</li>
			      <li>If you did not request this OTP, please disregard this email.</li>
			  </ul>
			  <p>If you have any questions or need assistance, feel free to reach out to our support team.</p>
			  <p>Thank you for choosing [Your Company Name]!</p>
			  <p>Best regards,<br>
			  [Your Name]<br>
			  [Your Position]<br>
			  [Your Company Name]<br>
			  [Your Contact Information]</p>
			""";
	public static final String OtpSubject = "Your One-Time Password (OTP) is Here!";
}
