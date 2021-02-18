package com.mcartagena.autoconfig;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;

import org.springframework.cloud.aws.mail.simplemail.SimpleEmailServiceMailSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;

@Configuration
public class SimpleEmailServiceAutoConfig {
    @Bean
    public AmazonSimpleEmailService amazonSimpleEmailService(AWSCredentialsProvider credentialProvider){
        return AmazonSimpleEmailServiceClientBuilder.standard()
            .withCredentials(credentialProvider)
            .withRegion(Regions.SA_EAST_1).build();
    }

    @Bean
    public MailSender mailSender(AmazonSimpleEmailService ses){
        return new SimpleEmailServiceMailSender(ses);
    }
    
}
