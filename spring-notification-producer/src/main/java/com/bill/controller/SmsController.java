package com.bill.controller;

import com.bill.service.NotificationService;
import com.bill.view.NotificationTemplate;
import com.bill.view.SmsMessage;
import com.bill.view.SmsOtpCode;
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
    private NotificationService<SmsMessage, SmsOtpCode> service;

    @Operation(summary = "SMS訊息傳送", description = "SMS訊息傳送")
    @PostMapping("/notification/sms/message/send")
    public String sendSmsMessage(@Valid @RequestBody NotificationTemplate<SmsMessage> message) {
        service.sendMessage(message.getBody());
        return "send message success";
    }

    @Operation(summary = "SMS OTP傳送", description = "SMS OTP傳送")
    @PostMapping("/notification/sms/otp/send")
    public String sendSmsOtpCode(@Valid @RequestBody NotificationTemplate<SmsOtpCode> otpCode) {
        service.sendOtpCode(otpCode.getBody());
        return "send otpcode success";
    }

    @Operation(summary = "SMS OTP驗證", description = "SMS OTP驗證")
    @PostMapping("/notification/sms/otp/verify")
    public String verifySmsOtpCode(@Valid @RequestBody NotificationTemplate<SmsOtpCode> otpCode) {
        return service.verifyOtpCode(otpCode.getBody());
    }
}
