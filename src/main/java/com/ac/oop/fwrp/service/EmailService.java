package com.ac.oop.fwrp.service;

public class EmailService {
    public void sendEmail(String to, String subject, String body) {
        // Implement email sending logic here
        // This could use JavaMail API or any other email service
        System.out.println("Sending email to " + to + " with subject: " + subject + " and body: " + body);
    }
}
