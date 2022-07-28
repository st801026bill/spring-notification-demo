package com.bill.service;

import com.bill.constant.KafkaConstant;
import com.bill.kafka.KafkaMsgDto;
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
  private KafkaTemplate<String, KafkaMsgDto> kafkaTemplate;

  public String sendKafka(String topic, KafkaMsgDto kafkaMsgDto) {
      log.info("KafkaMsgDto : {}", kafkaMsgDto.toString());

      ListenableFuture<SendResult<String, KafkaMsgDto>> future =
          kafkaTemplate.send(topic, kafkaMsgDto);

      future.addCallback(new KafkaSendCallback<String, KafkaMsgDto>() {
          @Override
          public void onSuccess(SendResult<String, KafkaMsgDto> result) {
              log.info("success send message:{} with offset:{} ", kafkaMsgDto, result.getRecordMetadata().offset());
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
