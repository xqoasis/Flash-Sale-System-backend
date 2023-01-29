package com.skillup.domain.promotion;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
@Slf4j
public class PromotionService {
    @Autowired
    PromotionRepository promotionRepository;

    @Resource(name = "${promotion.stock-strategy}")
    PromotionStockOperation promotionStockOperation;

    public PromotionDomain createPromotion(PromotionDomain promotionDomain) {
        promotionRepository.createPromotion(promotionDomain);
        return promotionDomain;
    }

    public PromotionDomain getPromotionById(String promotionId) {
        return promotionRepository.getPromotionByPromotionId(promotionId);
    }

    public List<PromotionDomain> getPromotionByStatus(Integer status) {
        return promotionRepository.getPromotionByPromotionStatus(status);
    }

    public boolean lockStock(String id) {
        return promotionStockOperation.lockStock(id);
    }

    public boolean deductStock(String id) {
        return promotionStockOperation.deductStock(id);
    }

    public boolean revertStock(String id) {
        return promotionStockOperation.revertStock(id);
    }

}
