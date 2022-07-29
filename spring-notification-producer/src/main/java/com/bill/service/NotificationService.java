package com.bill.service;

import com.bill.factory.NotificationFactory;
import com.bill.util.RandomUtil;
import com.bill.view.BaseMessage;
import com.bill.view.BaseOtpCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService<Message extends BaseMessage, OtpCode extends BaseOtpCode> {
    @Autowired
    private NotificationFactory<Message, OtpCode> factory;

    public void sendMessage(Message message) {
        IService<Message, OtpCode> service = factory.getNotification(message.getType());
        service.sendMessage(message);
    }

    public void sendOtpCode(OtpCode otpCode) {
        IService<Message, OtpCode> service = factory.getNotification(otpCode.getType());
        String otp = RandomUtil.genOtpCode(6);
        service.sendOtpCode(otpCode, otp);
    }

    public String verifyOtpCode(OtpCode otpCode) {
        IService<Message, OtpCode> service = factory.getNotification(otpCode.getType());
        Boolean isMatch = service.verifyOtpCode(otpCode);
        return isMatch?"verify otpcode success":"verify otpcode fail";
    }
}
