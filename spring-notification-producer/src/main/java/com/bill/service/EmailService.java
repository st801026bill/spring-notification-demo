package com.bill.service;

import com.bill.constant.KafkaConstant;
import com.bill.constant.RedisConstant;
import com.bill.util.JedisUtils;
import com.bill.view.MailMessage;
import com.bill.view.MailOtpCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService implements IService<MailMessage, MailOtpCode> {
    @Autowired
    private JedisUtils jedisUtils;

    @Autowired
    private KafkaService kafkaService;

    @Override
    public void sendMessage(MailMessage message) {
        kafkaService.sendKafka(KafkaConstant.MAIL_TOPIC, message.toPacket());
    }

    @Override
    public void sendOtpCode(MailOtpCode otpCode, String otp) {
        if(!protection(otpCode.getTo())) return;
        jedisUtils.setex(RedisConstant.getMailKey(otpCode.getTo()), otp, RedisConstant.EXIPIRE_TIME);
        kafkaService.sendKafka(KafkaConstant.MAIL_TOPIC, otpCode.toPacket(otp));
    }

    @Override
    public Boolean verifyOtpCode(MailOtpCode otpCode) {
        //1. get redis verifyKey
        String key = RedisConstant.getMailKey(otpCode.getTo());
        String verifyCode = jedisUtils.get(key);
        //2. verify with user input
        if(!StringUtils.equals(verifyCode, otpCode.getOtp())) return false;
        jedisUtils.del(key);
        return true;
    }

    private boolean protection(String to) {
        String blockKey = RedisConstant.getMailBlockKey(to);
        if(jedisUtils.incr(blockKey, RedisConstant.EXIPIRE_TIME) >= 4) {
            log.warn("The user is under blocking for sending otp code");
            return false;
        }
        return true;
    }
}
