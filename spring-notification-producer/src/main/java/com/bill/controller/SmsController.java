package com.bill.controller;

import com.bill.constant.KafkaConstant;
import com.bill.enums.NotificationEnum;
import com.bill.kafka.MessagePacket;
import com.bill.service.KafkaService;
import com.bill.view.NotificationTemplate;
import com.bill.view.SmsMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "SmsController", description = "SMS訊息發送服務")
@RestController
public class SmsController {

    @Autowired
    private KafkaService kafkaService;

    @Operation(summary = "SMS訊息傳送", description = "SMS訊息傳送")
    @PostMapping("/notification/sms/message/send")
    public String sendSmsMessage(@Valid @RequestBody NotificationTemplate<SmsMessage> template) {
        SmsMessage message = template.getBody();
        kafkaService.sendKafka(KafkaConstant.SMS_TOPIC, message.toPacket());
        return "success";
    }
}
