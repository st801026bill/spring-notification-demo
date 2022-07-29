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
public class NotificationFactory {

    @Autowired
    private SmsService smsService;
    @Autowired
    private EmailService emailSErvice;
    @Autowired
    private TelegramService telegramService;

    public IService getNotification(NotificationEnum e) {
        switch (e) {
            case SMS : return smsService;
            case EMAIL : return emailSErvice;
            case TELEGRAM: return telegramService;
            default: throw new NullPointerException();
        }
    }
}
