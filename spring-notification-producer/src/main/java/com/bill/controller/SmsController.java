package com.bill.controller;

import com.bill.kafka.KafkaMsgDto;
import com.bill.view.NotificationTemplate;
import com.bill.view.SmsMessage;
import com.bill.view.SmsOtpCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "SmsController", description = "SMS訊息發送服務")
@RestController
public class SmsController {
    @Operation(summary = "SMS訊息傳送", description = "SMS訊息傳送")
    @PostMapping("/notification/sms/message/send")
    public String sendSmsMessage(@Valid @RequestBody NotificationTemplate<SmsMessage> message) {
        return "";
    }
}
