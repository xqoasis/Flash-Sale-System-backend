package com.skillup.infrastructure.cache;

import com.alibaba.fastjson2.JSON;
import com.skillup.domain.promotionCache.PromotionCacheDomain;
import com.skillup.domain.promotionCache.PromotionCacheRepo;
import com.skillup.domain.promotionStockCache.PromotionStockCacheDomain;
import com.skillup.domain.promotionStockCache.PromotionStockCacheRepo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Objects;

@Repository
@Slf4j
public class RedisCacheRepo implements PromotionCacheRepo, PromotionStockCacheRepo {
    /**
     * 这里 RedisTemplate<KEY, VALUE> 中的value我们保存 FastJson2的JsonString，每次set，get调用FastJson2序列化/反序列化
     * FastJson2 会帮我们转化 LocalDateTime 为 String "2022-12-31T00:00:00"，
     */
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, JSON.toJSONString(value));
    }

    public String get(String key) {
        if (Objects.isNull(key)) return null;
        return redisTemplate.opsForValue().get(key);
    }

    public void delete(String key){
        if (Objects.nonNull(get(key))){
            redisTemplate.delete(key);
        }else{
            throw new RuntimeException("Key does not exist.");
        }
    }


    @Override
    public void setPromotionCacheDomain(PromotionCacheDomain promotionCacheDomain){
        set(promotionCacheDomain.getPromotionId(), promotionCacheDomain);
    }

    @Override
    public PromotionCacheDomain getPromotionById(String id){
        String jsonString = get(id);
        return JSON.parseObject(jsonString, PromotionCacheDomain.class);
    }
    @Override
    public Long getPromotionAvailableStock(String id) {
        String stockKey = PromotionStockCacheDomain.createPromotionStockKey(id);
        return JSON.parseObject(get(stockKey), Long.class);
    }
    @Override
    public void setPromotionAvailableStock(String id, Long stock) {
        set(PromotionStockCacheDomain.createPromotionStockKey(id), stock);
    }

    @Override
    public boolean lockStock(String id) {
        // 1. get stock;
        // 2. update stock = stock - 1;

        // ---lua script return Long
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        // 1) set lua script source
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("redis/lockStock.lua")));
        // 2) return type must be in: Long, Boolean, List, deserialized value
        redisScript.setResultType(Long.class);

        try {
            Long stock = redisTemplate.execute(redisScript, Arrays.asList(PromotionStockCacheDomain.createPromotionStockKey(id)));
            // TODO: modify return
            return !Objects.isNull(stock) && stock >= 0L;
        } catch (Throwable throwable) {
            log.error("Fail to lock stock for promotionCommodityKey: {}", PromotionStockCacheDomain.createPromotionStockKey(id));
            return false;
        }
    }

    @Override
    public boolean reverStock(String id) {
        return false;
    }




}
