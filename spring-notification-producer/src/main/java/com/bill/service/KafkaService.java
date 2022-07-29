package com.bill.service;

import com.bill.kafka.MessagePacket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaService {
  @Autowired
  private KafkaTemplate<String, MessagePacket> kafkaTemplate;

  public void sendKafka(String topic, MessagePacket packet) {
      log.info("MessagePacket : {}", packet.toString());
      kafkaTemplate.send(topic, packet);
  }
}
