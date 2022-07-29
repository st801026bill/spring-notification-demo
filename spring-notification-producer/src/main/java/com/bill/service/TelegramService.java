package com.bill.service;

import com.bill.constant.KafkaConstant;
import com.bill.constant.RedisConstant;
import com.bill.util.JedisUtils;
import com.bill.view.TelegramMessage;
import com.bill.view.TelegramOtpCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TelegramService implements IService<TelegramMessage, TelegramOtpCode> {

    @Autowired
    private JedisUtils jedisUtils;
    @Autowired
    private KafkaService kafkaService;

    @Override
    public void sendMessage(TelegramMessage message) {
        kafkaService.sendKafka(KafkaConstant.TELEGRAM_TOPIC, message.toPacket());
    }

    @Override
    public void sendOtpCode(TelegramOtpCode otpCode, String otp) {
        if(!protection()) return;
        jedisUtils.setex(RedisConstant.getTelegramKey(), otp, RedisConstant.EXIPIRE_TIME);
        kafkaService.sendKafka(KafkaConstant.TELEGRAM_TOPIC, otpCode.toPacket(otp));
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
