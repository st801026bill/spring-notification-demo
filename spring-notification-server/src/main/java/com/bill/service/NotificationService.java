package com.bill.service;

import com.bill.factory.NotificationFactory;
import com.bill.kafka.MessagePacket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    @Autowired
    private NotificationFactory factory;

    public void send(MessagePacket packet) {
        IService service = factory.getNotification(packet.getNotificationEnum());
        service.send(packet);
    }
}
