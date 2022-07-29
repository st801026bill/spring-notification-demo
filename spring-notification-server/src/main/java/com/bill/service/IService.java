package com.bill.service;

import com.bill.kafka.MessagePacket;

public interface IService {
    void send(MessagePacket packet);
}
