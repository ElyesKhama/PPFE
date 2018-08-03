package com.ppfe.business;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JSpinner.DateEditor;

import com.ppfe.entities.Warning;

public class SendMail {

	private static final String EMAIL_RECIPIENT = "elyesk@hotmail.fr";
	private static final String EMAIL_SENDER = "elyes91620@gmail.com";
	private static final String USERNAME = "elyes91620@gmail.com";
	private static final String PASSWORD = "mvrpuaawauyrxdke";
	private static final String HOST = "smtp.gmail.com";

	public static void send(ArrayList<Warning> listWarnings) {
		System.out.println("Sending message process start....");

		// Recipient's email ID needs to be mentioned.
		String to = EMAIL_RECIPIENT;

		// Sender's email ID needs to be mentioned
		String from = EMAIL_SENDER;
		final String username = USERNAME;// change accordingly
		final String password = PASSWORD;// change accordingly

		// Assuming you are sending email through relay.jangosmtp.net
		String host = HOST;

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

		// Get the Session object.
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		createMessage(session, from, to, listWarnings);

	}

	public static void createMessage(Session session, String from, String to, ArrayList<Warning> listWarnings) {
		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			// Set Subject: header field
			Date date = new Date(System.currentTimeMillis());
			message.setSubject("Warnings Report - " + date.toString());

			String textToSend = "Hello, this is the warnings report for : " + date.toString() + "\n \n";

			int i;
			for(i=0;i<listWarnings.size();i++) {
				textToSend += "Warning number : " + listWarnings.get(i).getId().toString() +" - ";
				textToSend += "Priority : " + listWarnings.get(i).getPriority() +" - ";
				textToSend += "With a difference of : " + String.valueOf(listWarnings.get(i).getCountDifference()) +" - ";
				textToSend += "On voucher : " + listWarnings.get(i).getPurchase().getVoucherType().getDescription() +" - ";
				textToSend += "\n";
			}
			
			// Now set the actual message
			message.setText(textToSend);

			// Send message
			Transport.send(message);

			System.out.println("Sent message successfully....");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
