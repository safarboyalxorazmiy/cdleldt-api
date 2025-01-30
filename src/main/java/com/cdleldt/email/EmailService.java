package com.cdleldt.email;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
@RequiredArgsConstructor
public class EmailService {
  private final JavaMailSender mailSender;

  public void sendStyledRegistrationCode(String toEmail, String registrationCode) {
    String subject = "Welcome to CDLELDT - Your Registration Code";
    String htmlContent = String.format(
      "<!DOCTYPE html>" +
        "<html>" +
        "<head>" +
        "<style>" +
        "  body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f4f4; }" +
        "  .email-container { max-width: 600px; margin: 20px auto; background: white; border-radius: 8px; box-shadow: 0 4px 8px rgba(0,0,0,0.1); }" +
        "  .header { background: #007BFF; color: white; padding: 10px 20px; text-align: center; font-size: 24px; font-weight: bold; border-top-left-radius: 8px; border-top-right-radius: 8px; }" +
        "  .content { padding: 20px; font-size: 16px; color: #333; }" +
        "  .code { font-size: 20px; color: #007BFF; font-weight: bold; text-align: center; margin: 20px 0; }" +
        "  .footer { text-align: center; font-size: 14px; color: #888; margin: 20px 0; }" +
        "</style>" +
        "</head>" +
        "<body>" +
        "  <div class='email-container'>" +
        "    <div class='header'>Welcome to CDLELDT</div>" +
        "    <div class='content'>" +
        "      <p>Dear costumer,</p>" +
        "      <p>Thank you for registering with us! Please use the following registration code to complete your setup:</p>" +
        "      <div class='code'>%s</div>" +
        "      <p>If you did not request this, please ignore this email.</p>" +
        "    </div>" +
        "    <div class='footer'>© 2025 CDLELDT. All rights reserved.</div>" +
        "  </div>" +
        "</body>" +
        "</html>", registrationCode
    );

    try {
      MimeMessage message = mailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message, true);

      helper.setTo(toEmail);
      helper.setSubject(subject);
      helper.setText(htmlContent, true); // `true` enables HTML parsing
      helper.setFrom("support@cdleldt.com");

      mailSender.send(message);
      System.out.println("Styled registration email sent successfully to " + toEmail);
    } catch (jakarta.mail.MessagingException e) {
      throw new RuntimeException(e);
    }
  }

}
