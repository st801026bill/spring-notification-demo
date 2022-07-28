package com.bill.factory;

import com.bill.constant.KafkaConstant;
import com.bill.enums.NotificationEnum;

public class KafkaTopicFactory {

    public String getKafkaTopic(String type) {
        return this.getKafkaTopic(NotificationEnum.findByType(type));
    }

    public String getKafkaTopic(NotificationEnum e) {
        switch (e) {
            case SMS: return KafkaConstant.SMS_TOPIC;
            default: throw new NullPointerException();
        }
    }
}
