package com.bill.config;

import com.bill.constant.KafkaConstant;
import com.bill.kafka.MessagePacket;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@EnableKafka
@Configuration
@Slf4j
public class KafkaConfig {

    public static final String DEFAULT_SERVER = "127.0.0.1:9092";
    public static final String SMS_GROUP = KafkaConstant.SMS_GROUP;
    public static final String MAIL_GROUP = KafkaConstant.MAIL_GROUP;
    public static final String TELEGRAM_GROUP = KafkaConstant.TELEGRAM_GROUP;

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MessagePacket> smsKafkaListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, MessagePacket> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(smsConsumerFactory());
        return factory;
    }
    
    @Bean
    public ConsumerFactory<String, MessagePacket> smsConsumerFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, DEFAULT_SERVER);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, SMS_GROUP);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(config);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MessagePacket> mailKafkaListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, MessagePacket> factory =
            new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(smsConsumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, MessagePacket> mailConsumerFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, DEFAULT_SERVER);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, MAIL_GROUP);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(config);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MessagePacket> telegramKafkaListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, MessagePacket> factory =
            new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(telegramConsumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, MessagePacket> telegramConsumerFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, DEFAULT_SERVER);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, TELEGRAM_GROUP);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(config);
    }
}