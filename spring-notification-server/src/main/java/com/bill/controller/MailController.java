package com.bill.controller;

import com.bill.service.NotificationService;
import com.bill.view.MailMessage;
import com.bill.view.MailOtpCode;
import com.bill.view.NotificationTemplate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "MailController", description = "Mail訊息發送服務")
@RestController
public class MailController {

    @Autowired
    private NotificationService<MailMessage, MailOtpCode> service;

    @Operation(summary = "Email訊息傳送", description = "EMAIL訊息傳送")
    @PostMapping("/notification/mail/message/send")
    public String sendMailNotification(@Valid @RequestBody NotificationTemplate<MailMessage> message) {
        service.sendMessage(message.getBody());
        return "send message success";
    }

    @Operation(summary = "Email OTP傳送", description = "EMAIL OTP傳送")
    @PostMapping("/notification/mail/otp/send")
    public String sendMailOtpCode(@Valid @RequestBody NotificationTemplate<MailOtpCode> otpCode) {
        service.sendOtpCode(otpCode.getBody());
        return "send otpcode success";
    }

    @Operation(summary = "Email OTP驗證", description = "EMAIL OTP驗證")
    @PostMapping("/notification/mail/otp/verify")
    public String verifyMailOtpCode(@Valid @RequestBody NotificationTemplate<MailOtpCode> otpCode) {
        return service.verifyOtpCode(otpCode.getBody());
    }
}
