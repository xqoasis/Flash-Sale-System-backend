package com.skillup.domain.commodity;

import org.springframework.stereotype.Repository;

@Repository
public interface CommodityRepository {
    void createCommodity(CommodityDomain commodityDomain);
    CommodityDomain getCommodityByCommodityId(String commodityId);
}
