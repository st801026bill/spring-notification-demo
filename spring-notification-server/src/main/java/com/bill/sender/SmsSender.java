package com.bill.sender;

import com.bill.config.SmsConfig;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
public class SmsSender {

    @Autowired
    private SmsConfig smsConfig;
    private static SmsConfig SMS_CONFIG;

    @PostConstruct
    public void init() {
        SMS_CONFIG = smsConfig;
    }

    public static void send(String to, String msg) {
        Twilio.init(SMS_CONFIG.getAccountSid(), SMS_CONFIG.getAuthToken());
        Message message = Message.creator(new PhoneNumber(to), new PhoneNumber(SMS_CONFIG.getSender()), msg).create();
        log.info("message sid: {}", message.getSid());
    }
}
