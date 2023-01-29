package com.skillup.domain.promotionStockCache;

public interface PromotionStockCacheRepo {
    boolean lockStock(String id);
    boolean reverStock(String id);
    Long getPromotionAvailableStock(String id);
    void setPromotionAvailableStock(String id, Long stock);
}
