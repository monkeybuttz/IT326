package com.arijuncodes.SpringEmailDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService 
{
    @Autowired
    private JavaMailSender mailSender;
    public void sendEmail(String toEmail, String subject, String body, int code)
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("it326PetCare@gmail.com");
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body + code);
        mailSender.send(message);
        System.out.println("Mail Sent Successfully...");
    }

    
}
