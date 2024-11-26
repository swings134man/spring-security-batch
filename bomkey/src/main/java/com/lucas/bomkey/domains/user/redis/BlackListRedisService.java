package com.lucas.bomkey.domains.user.redis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BlackListRedisService {
    private final StringRedisTemplate stringRedisTemplate;

    public void setToken(String token) {
        // expire time 1 hour
        stringRedisTemplate.opsForValue().set(token, "true", 1, java.util.concurrent.TimeUnit.HOURS);
    }

    public boolean isBlackList(String token) {
        // null 이면 false
        String isExist = stringRedisTemplate.opsForValue().get(token);
        if (isExist == null) {
            return false;
        }
        return true;
    }
}
