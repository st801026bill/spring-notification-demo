package com.bill.service;

import com.bill.kafka.MessagePacket;
import com.bill.sender.SmsSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SmsService implements IService {
    @Override
    public void send(MessagePacket packet) {
        SmsSender.send(packet.getTo(), packet.getMessage());
    }
}
