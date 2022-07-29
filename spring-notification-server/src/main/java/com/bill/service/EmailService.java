package com.bill.service;

import com.bill.kafka.MessagePacket;
import com.bill.sender.EmailSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
@Slf4j
public class EmailService implements IService {
    @Override
    public void send(MessagePacket packet) {
        try {
            EmailSender.send(packet.getTo(), "[SUBJECT]", packet.getMessage());
        } catch (MessagingException e) {
            log.error("send mail failed!!");
            throw new RuntimeException("send mail failed!!");
        }
    }
}
