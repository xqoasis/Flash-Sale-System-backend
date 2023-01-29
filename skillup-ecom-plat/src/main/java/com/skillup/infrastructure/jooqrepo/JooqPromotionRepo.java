package com.skillup.infrastructure.jooqrepo;

import com.skillup.domain.promotion.PromotionDomain;
import com.skillup.domain.promotion.PromotionRepository;
import com.skillup.domain.promotion.PromotionStockOperation;
import com.skillup.infrastructure.jooq.tables.Promotion;
import com.skillup.infrastructure.jooq.tables.records.PromotionRecord;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "optimistic-lock")
@Slf4j
public class JooqPromotionRepo implements PromotionRepository, RecordDomainMapping<PromotionRecord, PromotionDomain>, PromotionStockOperation {
    @Autowired
    DSLContext dslContext;
    private static final Promotion P_T = new Promotion();

    @Override
    public void createPromotion(PromotionDomain promotionDomain) {
        dslContext.executeInsert(toRecord(promotionDomain));
    }

    @Override
    public PromotionDomain getPromotionByPromotionId(String promotionId) {
        return dslContext.selectFrom(P_T).where(P_T.PROMOTION_ID.eq(promotionId))
                .fetchOptional(this::toDomain).orElse(null);
    }

    @Override
    public List<PromotionDomain> getPromotionByPromotionStatus(Integer status) {
        return dslContext.selectFrom(P_T).where(P_T.STATUS.eq(status)).fetch(this::toDomain);
    }

    @Override
    public void updatePromotion(PromotionDomain promotionDomain) {
        dslContext.executeUpdate(toRecord(promotionDomain));

    }

    @Override
    public boolean lockStock(String id) {
        /**
         * update promotion
         * set available_stock = available_stock - 1, lock_stock = lock_stock + 1
         * where id = promotion_id and available_stock > 0
         */
        log.info("(optimistic-lock) try to lock");
        int isLocked = dslContext.update(P_T)
                .set(P_T.AVAILABLE_STOCK, P_T.AVAILABLE_STOCK.subtract(1))
                .set(P_T.LOCK_STOCK, P_T.LOCK_STOCK.add(1))
                .where(P_T.PROMOTION_ID.eq(id).and(P_T.AVAILABLE_STOCK.greaterThan(0L)))
                .execute();
        return isLocked == 1;
    }

    @Override
    public boolean deductStock(String id) {
        /**
         * update promotion
         * set lock_stock = lock_stock - 1
         * where id = promotion_id and lock_stock > 0
         */
        log.info("(optimistic-lock) try to deduct");
        int isLocked = dslContext.update(P_T)
                .set(P_T.LOCK_STOCK, P_T.LOCK_STOCK.subtract(1))
                .where(P_T.PROMOTION_ID.eq(id).and(P_T.LOCK_STOCK.greaterThan(0L)))
                .execute();
        return isLocked == 1;
    }

    @Override
    public boolean revertStock(String id) {
        /**
         * update promotion
         * set available_stock = available_stock + 1, lock_stock = lock_stock - 1
         * where id = promotion_id and lock_stock > 0
         */
        log.info("(optimistic-lock) try to revert");
        int isReverted = dslContext.update(P_T)
                .set(P_T.AVAILABLE_STOCK, P_T.AVAILABLE_STOCK.add(1))
                .set(P_T.LOCK_STOCK, P_T.LOCK_STOCK.subtract(1))
                .where(P_T.PROMOTION_ID.eq(id).and(P_T.LOCK_STOCK.greaterThan(0L)))
                .execute();
        return isReverted == 1;
    }

    @Override
    public PromotionRecord toRecord(PromotionDomain promotionDomain){
        return new PromotionRecord(
                promotionDomain.getPromotionId(),
                promotionDomain.getPromotionName(),
                promotionDomain.getCommodityId(),
                promotionDomain.getOriginalPrice(),
                promotionDomain.getPromotionalPrice(),
                promotionDomain.getStartTime(),
                promotionDomain.getEndTime(),
                promotionDomain.getStatus(),
                promotionDomain.getTotalStock(),
                promotionDomain.getAvailableStock(),
                promotionDomain.getLockStock(),
                promotionDomain.getImageUrl()
        );


    }
    @Override
    public PromotionDomain toDomain(PromotionRecord promotionRecord){
        return PromotionDomain.builder()
                .promotionId(promotionRecord.getPromotionId())
                .promotionName(promotionRecord.getPromotionName())
                .commodityId(promotionRecord.getCommodityId())
                .originalPrice(promotionRecord.getOriginalPrice())
                .promotionalPrice(promotionRecord.getPromotionPrice())
                .startTime(promotionRecord.getStartTime())
                .endTime(promotionRecord.getEndTime())
                .status(promotionRecord.getStatus())
                .totalStock(promotionRecord.getTotalStock())
                .availableStock(promotionRecord.getAvailableStock())
                .lockStock(promotionRecord.getLockStock())
                .imageUrl(promotionRecord.getImageUrl())
                .build();
    }
}
