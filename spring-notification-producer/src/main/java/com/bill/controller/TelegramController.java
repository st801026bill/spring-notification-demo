package com.bill.controller;

import com.bill.service.NotificationService;
import com.bill.view.NotificationTemplate;
import com.bill.view.TelegramMessage;
import com.bill.view.TelegramOtpCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "TelegramController", description = "Telegram訊息發送服務")
@RestController
public class TelegramController {

    @Autowired
    private NotificationService<TelegramMessage, TelegramOtpCode> service;

    @Operation(summary = "Telegram訊息傳送", description = "EMAIL訊息傳送")
    @PostMapping("/notification/telegram/message/send")
    public String sendTelegramNotification(@Valid @RequestBody NotificationTemplate<TelegramMessage> message) {
        service.sendMessage(message.getBody());
        return "send message success";
    }

    @Operation(summary = "Telegram OTP傳送", description = "EMAIL OTP傳送")
    @PostMapping("/notification/telegram/otp/send")
    public String sendTelegramOtpCode(@Valid @RequestBody NotificationTemplate<TelegramOtpCode> otpCode) {
        service.sendOtpCode(otpCode.getBody());
        return "send otpcode success";
    }

    @Operation(summary = "Telegram OTP驗證", description = "Telegram OTP驗證")
    @PostMapping("/notification/telegram/otp/verify")
    public String verifyTelegramOtpCode(@Valid @RequestBody NotificationTemplate<TelegramOtpCode> otpCode) {
        return service.verifyOtpCode(otpCode.getBody());
    }
}
