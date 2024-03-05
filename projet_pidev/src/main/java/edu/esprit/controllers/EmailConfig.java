package edu.esprit.controllers;

import com.emailsender.sendemail.SendEmail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfig {

  /*  @Bean
    public SendEmail sendEmail(JavaMailSender javaMailSender) {
        return new SendEmail(javaMailSender);
    }

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587); // Use your mail server port
        mailSender.setUsername("Innohire45@gmail.com");
        mailSender.setPassword("xcuu kred fyux jigy");

        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        mailSender.setJavaMailProperties(properties);

        return mailSender;
    }*/
}
