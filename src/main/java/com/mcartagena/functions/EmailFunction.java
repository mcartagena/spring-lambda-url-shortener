package com.mcartagena.functions;

import java.util.function.Function;

import com.mcartagena.functiontypes.EmailDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component("email")
public class EmailFunction implements Function<EmailDetails, Boolean>{

    @Autowired
    private MailSender mailSender;

    @Override
    public Boolean apply(EmailDetails emailDetails) {
        
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailDetails.getFromEmailAddress());
        message.setTo(emailDetails.getToEmailAddress());
        message.setSubject(emailDetails.getSubject());

        StringBuilder emailBody = new StringBuilder();
        emailBody.append("Hi, \n\nYou have successfully created a short code base on this URL: ")
        .append(emailDetails.getLongURL()).append("\n")
        .append("Your short code is: ")
        .append(emailDetails.getShortCode()).append("\n\n")
        .append("Thank you\n")
        .append("Spring Cloud Function URL Shortener running on AWS Lambda");

        message.setText(emailBody.toString());

        try {
            mailSender.send(message);
            return true;
            
        } catch (MailException e) {
            System.out.println(e);
            return false;
        }

    }

    
    
}
