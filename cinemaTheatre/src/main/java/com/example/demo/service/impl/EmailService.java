package com.example.demo.service.impl;

import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {


    @Autowired
    private JavaMailSender javaMailSender;

    @Async
    public void sendEmailVerification(String to, String body) throws MailException, MessagingException {

        MimeMessage mail = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mail, true, "UTF-8");

        helper.setTo(to);
        helper.setFrom("${spring.mail.username}");
        helper.setSubject("Verify email address");
        helper.setText("To verify your account please click on the verification link below: \n" + body, true);
        javaMailSender.send(mail);

//        System.out.println("Email poslat!");
//        SimpleMailMessage mail = new SimpleMailMessage();
//        mail.setTo(to);
//        mail.setFrom(environment.getProperty("${spring.mail.username}"));
//        mail.setSubject("Verify email address");
//        mail.setText("To verify your account please click on the verification link below: \n" + body);
//        javaMailSender.send(mail);

    }
}
