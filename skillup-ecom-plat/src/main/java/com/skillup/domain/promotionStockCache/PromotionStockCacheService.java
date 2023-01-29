package com.skillup.domain.promotionStockCache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromotionStockCacheService {
    @Autowired
    PromotionStockCacheRepo promotionStockCacheRepo;
    public boolean lockStock(String id){
        return promotionStockCacheRepo.lockStock(id);
    }
    public Long getStock(String id){
        return promotionStockCacheRepo.getPromotionAvailableStock(id);
    }
    public void setStock(String id, Long stock){
        promotionStockCacheRepo.setPromotionAvailableStock(id, stock);
    }

}
