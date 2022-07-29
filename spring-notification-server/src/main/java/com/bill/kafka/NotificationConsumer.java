package com.bill.kafka;

import com.bill.constant.KafkaConstant;
import com.bill.enums.NotificationEnum;
import com.bill.service.NotificationService;
import com.bill.view.SmsMessage;
import com.bill.view.SmsOtpCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationConsumer {

    @Autowired
    private NotificationService<SmsMessage, SmsOtpCode> service;

    @KafkaListener(topics = KafkaConstant.SMS_TOPIC, groupId = KafkaConstant.SMS_GROUP,
        containerFactory = "smsKafkaListenerFactory")
    public void consumeSms(MessagePacket packet) {
        log.info("consumed sms message: {} ", packet);

        log.info("Consumed done. ");
    }

    @KafkaListener(topics = KafkaConstant.MAIL_TOPIC, groupId = KafkaConstant.MAIL_GROUP,
        containerFactory = "mailKafkaListenerFactory")
    public void consumeMail(MessagePacket packet) {
      log.info("consumed mail message: {} ", packet);

      log.info("Consumed done. ");
    }

    @KafkaListener(topics = KafkaConstant.TELEGRAM_TOPIC, groupId = KafkaConstant.TELEGRAM_GROUP,
        containerFactory = "telegramKafkaListenerFactory")
    public void consumeTelegram(MessagePacket packet) {
        log.info("consumed telegram message: {} ", packet);

        log.info("Consumed done. ");
    }
}
