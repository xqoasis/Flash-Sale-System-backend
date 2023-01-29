package com.skillup.domain.promotionCache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromotionCacheService {
    @Autowired
    PromotionCacheRepo promotionCacheRepo;
    public void setPromotionCacheDomain(PromotionCacheDomain promotionCacheDomain){
        promotionCacheRepo.setPromotionCacheDomain(promotionCacheDomain);
    }
    public PromotionCacheDomain getPromotionCacheDomainById(String id){
        return promotionCacheRepo.getPromotionById(id);
    }

}
