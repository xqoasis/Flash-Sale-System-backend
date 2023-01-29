package com.skillup.domain.promotionStockCache;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PromotionStockCacheDomain {
    private String promotionId;
    private String availableStock;

    public static final String PROMOTION_PREFIX = "PROMOTION_";
    public static final String STOCK_SUFFIX = "_STOCK";
    public static String createPromotionStockKey(String promotionId){
        return PROMOTION_PREFIX + promotionId + STOCK_SUFFIX;
    }
}
