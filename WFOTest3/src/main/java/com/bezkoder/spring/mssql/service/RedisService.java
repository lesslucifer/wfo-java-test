package com.bezkoder.spring.mssql.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Slf4j
public class RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @PostConstruct
    public void testConnection() {
        try {
            redisTemplate.opsForValue().set("WFOTest3", "Redis connection successful");
            String result = (String) redisTemplate.opsForValue().get("test");
            log.info("Redis connection test result: {}", result);
        } catch (Exception e) {
            log.error("Failed to connect to Redis: {}", e.getMessage());
        }
    }
} 