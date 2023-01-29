package com.skillup.application.promotion;

import com.skillup.application.promotion.util.CacheDomainMapper;
import com.skillup.domain.promotion.PromotionDomain;
import com.skillup.domain.promotion.PromotionService;
import com.skillup.domain.promotionCache.PromotionCacheDomain;
import com.skillup.domain.promotionCache.PromotionCacheService;
import com.skillup.domain.promotionStockCache.PromotionStockCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PromotionApplicationService {

    @Autowired
    PromotionService promotionService;

    @Autowired
    PromotionCacheService promotionCacheService;

    @Autowired
    PromotionStockCacheService promotionStockCacheService;

    public PromotionDomain getPromotionById(String id) {
        // 1. try to hit cache
        PromotionCacheDomain cached = promotionCacheService.getPromotionCacheDomainById(id);
        // 2. doesn't hit, 2.1 access database 2.2 set cache
        if (Objects.isNull(cached)) {
            PromotionDomain promotionDomain = promotionService.getPromotionById(id);
            if (Objects.isNull(promotionDomain)) return null;
            promotionCacheService.setPromotionCacheDomain(CacheDomainMapper.domainToCache(promotionDomain));
            return promotionDomain;
        }
        // 2. if hit, availableStock may not accurate, cover availableStock by promotionStockCache, then return
        Long availableStock = promotionStockCacheService.getStock(id);
        cached.setAvailableStock(availableStock);

        return CacheDomainMapper.cacheToDomain(cached);
    }


}