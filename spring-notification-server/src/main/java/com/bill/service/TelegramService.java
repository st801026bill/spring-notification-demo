package com.bill.service;

import com.bill.constant.RedisConstant;
import com.bill.sender.TelegramSender;
import com.bill.util.JedisUtils;
import com.bill.util.RandomUtil;
import com.bill.view.SmsOtpCode;
import com.bill.view.TelegramMessage;
import com.bill.view.TelegramOtpCode;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TelegramService implements IService<TelegramMessage, TelegramOtpCode> {
    @Autowired
    private JedisUtils jedisUtils;

    @Override
    public void sendMessage(TelegramMessage message) {
        try {
            TelegramSender.send(message.getMessage());
        } catch (IOException e) {
            log.error("telegram send fail, {}", e.getMessage());
        }
    }

    @Override
    public void sendOtpCode(TelegramOtpCode otpCode, String otp) {
        try {
            if(!protection()) return;
            jedisUtils.setex(RedisConstant.getTelegramKey(), otp, RedisConstant.EXIPIRE_TIME);
            TelegramSender.send(otp);
        } catch (IOException e) {
            log.error("telegram send fail, {}", e.getMessage());
        }
    }

    @Override
    public Boolean verifyOtpCode(TelegramOtpCode otpCode) {
        //1. get redis verifyKey
        String key = RedisConstant.getTelegramKey();
        String verifyCode = jedisUtils.get(key);
        //2. verify with user input
        if(!StringUtils.equals(verifyCode, otpCode.getOtp())) return false;
        jedisUtils.del(key);
        return true;
    }

    private boolean protection() {
        String blockKey = RedisConstant.getTelegramBlockKey();
        if(jedisUtils.incr(blockKey, RedisConstant.EXIPIRE_TIME) >= 4) {
            log.warn("The user is under blocking for sending otp code");
            return false;
        }
        return true;
    }
}
