package com.skillup.domain.promotion;

public interface PromotionStockOperation {
    boolean lockStock(String id);
    boolean deductStock(String id);
    boolean revertStock(String id);
}
