package com.skillup.domain.promotionCache;

public interface PromotionCacheRepo {
    void setPromotionCacheDomain(PromotionCacheDomain promotionCacheDomain);
    PromotionCacheDomain getPromotionById(String id);
}
