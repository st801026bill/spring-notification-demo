package com.bill.sender;

import com.bill.config.EmailConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class EmailSender {

    @Autowired
    private EmailConfig emailConfig;
    private static EmailConfig EMAI_CONFIG;

    @Autowired
    private Session session;
    private static Session SESSION;

    @PostConstruct
    public void init() {
        EMAI_CONFIG = emailConfig;
        SESSION = session;
    }

    public static void send(String to, String subject, String msg) throws MessagingException {
        Message message = new MimeMessage(SESSION);

        message.setFrom(new InternetAddress(EMAI_CONFIG.getFrom()));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        message.setText(msg);

        Transport.send(message);
    }
}
