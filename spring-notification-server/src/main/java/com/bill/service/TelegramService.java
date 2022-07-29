package com.bill.service;

import com.bill.kafka.MessagePacket;
import com.bill.sender.TelegramSender;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TelegramService implements IService {
    @Override
    public void send(MessagePacket packet) {
        try {
            TelegramSender.send(packet.getMessage());
        } catch (IOException e) {
            log.error("send telegram failed!!");
            throw new RuntimeException("send telegram failed!!");
        }
    }
}
