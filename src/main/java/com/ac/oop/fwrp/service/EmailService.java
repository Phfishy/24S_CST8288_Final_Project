package com.ac.oop.fwrp.service;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class EmailService {
    private final String username = "test@gmail.com";//TODO need to change real email
    private final String password = "test-password";
    public void sendEmail(String to, String subject, String body) {
        // Implement email sending logic here
        // This could use JavaMail API or any other email service
        System.out.println("Sending email to " + to + " with subject: " + subject + " and body: " + body);
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);
            Transport.send(message);
            System.out.println("Email sent successfully to " + to);
        } catch (MessagingException e) {
            e.printStackTrace();
            System.err.println("Failed to send email to " + to + ": " + e.getMessage());
        }
    }
}
