package com.bill.util;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class JedisUtils {
    @Autowired
    private JedisPool jedisPool;

    public void set(String key, String value) {
        try (Jedis jedis = jedisPool.getResource()){
            jedis.set(key, value);// return OK
        }
    }

    public void hmset(String key, HashMap<String, String> map) {
        try (Jedis jedis = jedisPool.getResource()){
            jedis.hmset(key, map);// return OK
        }
    }

    public void hset(String key, String field, String value) {
        try (Jedis jedis = jedisPool.getResource()){
            jedis.hset(key, field, value);// return OK
        }
    }

    public String setex(String key, String value, Long expire) {
        try (Jedis jedis = jedisPool.getResource()){
            return jedis.setex(key, expire, value);
        }
    }

    public Map<String, String> hgetall(String key) {
        try (Jedis jedis = jedisPool.getResource()){
            return jedis.hgetAll(key);
        }
    }

    public String get(String key) {
        try (Jedis jedis = jedisPool.getResource()){
            return jedis.get(key);
        }
    }

    public String hget(String key, String field) {
        try (Jedis jedis = jedisPool.getResource()){
            return jedis.hget(key, field);
        }
    }

    public Long del(String key) {
        try (Jedis jedis = jedisPool.getResource()){
            return jedis.del(key);
        }
    }

    public Long incr(String key, Long expire) {
        try (Jedis jedis = jedisPool.getResource()){
            long num = jedis.incr(key);
            if(num == 1L) jedis.expire(key, expire);
            return num;
        }
    }
}
