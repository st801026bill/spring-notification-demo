package com.bill.factory;

import com.bill.enums.NotificationEnum;
import com.bill.service.EmailService;
import com.bill.service.IService;
import com.bill.service.SmsService;
import com.bill.service.TelegramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class NotificationFactory<Message, OtpCode> {

    @Autowired
    private SmsService smsService;
    @Autowired
    private EmailService emailSErvice;
    @Autowired
    private TelegramService telegramService;

//    private static SmsService SMS_SERVICE;
//    private static EmailService EMAIL_SERVICE;
//    private static TelegramService TELEGRAM_SERVICE;

//    @PostConstruct
//    public void init() {
//        SMS_SERVICE = smsService;
//        EMAIL_SERVICE = emailSErvice;
//        TELEGRAM_SERVICE = telegramService;
//    }

    public IService<Message, OtpCode> getNotification(String type) {
        switch (NotificationEnum.findByType(type)) {
            case SMS : return (IService<Message, OtpCode>) smsService;
            case EMAIL : return (IService<Message, OtpCode>) emailSErvice;
            case TELEGRAM: return (IService<Message, OtpCode>) telegramService;
            default: throw new NullPointerException();
        }
    }
}
