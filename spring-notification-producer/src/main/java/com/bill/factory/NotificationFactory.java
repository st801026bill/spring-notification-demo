package com.bill.factory;

import com.bill.enums.NotificationEnum;
import com.bill.service.EmailService;
import com.bill.service.IService;
import com.bill.service.SmsService;
import com.bill.service.TelegramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationFactory<Message, OtpCode> {

    @Autowired
    private SmsService smsService;
    @Autowired
    private EmailService emailSErvice;
    @Autowired
    private TelegramService telegramService;

    public IService<Message, OtpCode> getNotification(NotificationEnum e) {
        switch (e) {
            case SMS : return (IService<Message, OtpCode>) smsService;
            case EMAIL : return (IService<Message, OtpCode>) emailSErvice;
            case TELEGRAM: return (IService<Message, OtpCode>) telegramService;
            default: throw new NullPointerException();
        }
    }

    public IService<Message, OtpCode> getNotification(String type) {
        return getNotification(NotificationEnum.findByType(type));
    }
}
