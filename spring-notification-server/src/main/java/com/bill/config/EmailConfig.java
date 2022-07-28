package com.bill.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

@Configuration
@Data
public class EmailConfig {
    @Value("${mailtrap.host}")
    private String host;
    @Value("${mailtrap.port}")
    private String port;
    @Value("${mailtrap.userId}")
    private String userId;
    @Value("${mailtrap.password}")
    private String password;
    @Value("${mailtrap.starttls}")
    private boolean starttls;
    @Value("${mailtrap.auth}")
    private boolean auth;


    @Value("${mailtrap.from}")
    private String from;

    @Bean
    public Session session() {
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", auth);
        props.put("mail.smtp.starttls.enable", starttls);

        return Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(userId, password);
                }
            });
    }
}