package com.bill.service;

import com.bill.constant.KafkaConstant;
import com.bill.constant.RedisConstant;
import com.bill.util.JedisUtils;
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

    @Autowired
    private KafkaService kafkaService;

    @Override
    public void sendMessage(SmsMessage message) {
        kafkaService.sendKafka(KafkaConstant.SMS_TOPIC, message.toPacket());
    }

    @Override
    public void sendOtpCode(SmsOtpCode otpCode, String otp) {
        if(!protection(otpCode.getTo())) return;
        jedisUtils.setex(RedisConstant.getSmsKey(otpCode.getTo()), otp, RedisConstant.EXIPIRE_TIME);
        kafkaService.sendKafka(KafkaConstant.SMS_TOPIC, otpCode.toPacket(otp));
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
