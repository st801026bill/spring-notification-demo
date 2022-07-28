package com.bill.service;

import com.bill.kafka.MessagePacket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Service
@Slf4j
public class KafkaService {
  @Autowired
  private KafkaTemplate<String, MessagePacket> kafkaTemplate;

  public String sendKafka(String topic, MessagePacket packet) {
      log.info("KafkaMsgDto : {}", packet.toString());

      ListenableFuture<SendResult<String, MessagePacket>> future =
          kafkaTemplate.send(topic, packet);

      future.addCallback(new KafkaSendCallback<String, MessagePacket>() {
          @Override
          public void onSuccess(SendResult<String, MessagePacket> result) {
              log.info("success send message:{} with offset:{} ", packet, result.getRecordMetadata().offset());
          }

          @Override
          public void onFailure(KafkaProducerException ex) {
              log.error("fail send message! Do somthing....");
              log.error("ex: {}", ex.getMessage());
          }
      });

    return "Published done";
  }
}
