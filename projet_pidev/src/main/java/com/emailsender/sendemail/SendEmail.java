package com.emailsender.sendemail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendEmail {
    @Autowired
    private JavaMailSender sender ;
    public void send (String toEmailAdd,String Subject,String Message)
    {
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setFrom("Innohire45@gmail.com");
        smm.setTo(toEmailAdd);
        smm.setSubject(Subject);
        smm.setText(Message);
        sender.send(smm);
    }


}
