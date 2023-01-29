package com.skillup.domain.commodity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommodityService {
    @Autowired
    CommodityRepository commodityRepository;
    public CommodityDomain registerCommodity(CommodityDomain commodityDomain){
        commodityRepository.createCommodity(commodityDomain);

        return commodityDomain;
    }

    public CommodityDomain getCommodityById(String id){
        return commodityRepository.getCommodityByCommodityId(id);
    }
}
