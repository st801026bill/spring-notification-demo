package com.bill.service;

import com.bill.constant.RedisConstant;
import com.bill.sender.SmsSender;

import com.bill.util.JedisUtils;
import com.bill.util.RandomUtil;
import com.bill.view.MailOtpCode;
import com.bill.view.SmsMessage;
import com.bill.view.SmsOtpCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SmsService implements IService<SmsMessage, SmsOtpCode> {
    @Autowired
    private JedisUtils jedisUtils;

    @Override
    public void sendMessage(SmsMessage message) {
        SmsSender.send(message.getTo(), message.getMessage());
    }

    @Override
    public void sendOtpCode(SmsOtpCode otpCode, String otp) {
        if(!protection(otpCode.getTo())) return;
        jedisUtils.setex(RedisConstant.getSmsKey(otpCode.getTo()), otp, RedisConstant.EXIPIRE_TIME);
        SmsSender.send(otpCode.getTo(), otp);
    }

    @Override
    public Boolean verifyOtpCode(SmsOtpCode otpCode) {
        //1. get redis verifyKey
        String key = RedisConstant.getSmsKey(otpCode.getTo());
        String verifyCode = jedisUtils.get(key);
        //2. verify with user input
        if(!StringUtils.equals(verifyCode, otpCode.getOtp())) return false;
        jedisUtils.del(key);
        return true;
    }

    private boolean protection(String to) {
        String blockKey = RedisConstant.getSmsBlockKey(to);
        if(jedisUtils.incr(blockKey, RedisConstant.EXIPIRE_TIME) >= 4) {
            log.warn("The user is under blocking for sending otp code");
            return false;
        }
        return true;
    }
}
