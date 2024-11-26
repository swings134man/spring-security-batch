package com.lucas.bomkey.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/************
 * @info : Redis Config
 * @name : RedisRepositoryConfig
 * @date : 2024. 11. 26. 오후 4:55
 * @author : SeokJun Kang(swings134@gmail.com)
 * @version : 1.0.0
 * @Description :
 ************/
@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableRedisRepositories
@EnableCaching
public class RedisRepositoryConfig {

    @Value("${spring.data.redis.password}")
    private String redisPassword;

    private final RedisProperties redisProperties;


    // lettuce
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(redisProperties.getHost());
        config.setPort(redisProperties.getPort());
        config.setPassword(redisPassword);

        return new LettuceConnectionFactory(config);
    }

    // redisTemplate: default Value Type = JSON
    // If you want to use String Type, you can change ValueSerializer to StringRedisSerializer or Use StringRedisTemplate
    @Bean
    public RedisTemplate<?, ?> redisTemplate() {
        RedisTemplate<?, ?> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());   //connection
        redisTemplate.setKeySerializer(new StringRedisSerializer());    // key
//        redisTemplate.setValueSerializer(new StringRedisSerializer());  // value
//        redisTemplate.setKeySerializer(new Jackson2JsonRedisSerializer<>(String.class)); //Java Obj <-> JSON -> String KEY
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(String.class)); //Java Obj <-> JSON -> String Value
        return redisTemplate;
    }


    @Bean
    public StringRedisTemplate stringRedisTemplate() {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(redisConnectionFactory());
        return stringRedisTemplate;
    }
}