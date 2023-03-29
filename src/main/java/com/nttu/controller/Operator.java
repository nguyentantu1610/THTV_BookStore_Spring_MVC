package com.nttu.controller;

import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.util.Locale;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class Operator {
	
	@Autowired
	private JavaMailSender mailSender;

	protected static String convertUTF8(String string) throws UnsupportedEncodingException {
		return new String(string.getBytes("ISO-8859-1"), "UTF-8");
	}

	protected String convertCost(int cost) {
		Locale locale = new Locale("vi", "VN");
		NumberFormat format = NumberFormat.getCurrencyInstance(locale);
		return format.format(cost);
	}
	
	protected void sendMailCustom (String to, String text, String subject) throws MessagingException {
		// creates a simple e-mail object
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(text, true);
		// sends the e-mail
		mailSender.send(mimeMessage);
	}
}
