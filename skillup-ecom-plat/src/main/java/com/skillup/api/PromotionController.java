package com.skillup.api;

import com.skillup.api.dto.in.PromotionInDto;
import com.skillup.api.dto.out.PromotionOutDto;
import com.skillup.api.util.ResponseUtil;
import com.skillup.application.promotion.PromotionApplicationService;
import com.skillup.domain.promotion.PromotionDomain;
import com.skillup.domain.promotion.PromotionService;
import com.skillup.domain.promotionStockCache.PromotionStockCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/promotion")
public class PromotionController {
    @Autowired
    PromotionService promotionService;
    @Autowired
    PromotionApplicationService promotionApplicationService;
    @Autowired
    PromotionStockCacheService promotionStockCacheService;
    @PostMapping
    public ResponseEntity<PromotionOutDto> createPromotion(@RequestBody PromotionInDto promotionInDto){
        PromotionDomain promotionDomainSaved = promotionService.createPromotion(toDomain(promotionInDto));
        return ResponseEntity.status(ResponseUtil.SUCCESS)
                .body(toPromotionOutDto(promotionDomainSaved));

    }
    @GetMapping("/id/{id}")
    public ResponseEntity<PromotionOutDto> getPromotionById(@PathVariable("id") String id){
        // if hit promotion cache, stock may not accurate
        PromotionDomain promotionDomain = promotionApplicationService.getPromotionById(id);
        if (Objects.isNull(promotionDomain)){
            return ResponseEntity.status(ResponseUtil.BAD_REQUEST).body(null);
        }

        return ResponseEntity.status((ResponseUtil.SUCCESS))
                .body(toPromotionOutDto(promotionDomain));
    }
    @GetMapping("/status/{status}")
    public ResponseEntity<List<PromotionOutDto>> getPromotionByStatus(@PathVariable("status") Integer status){
        List<PromotionDomain> promotionDomainList = promotionService.getPromotionByStatus(status);
//        if (Objects.isNull(promotionDomain)){
//            return ResponseEntity.status(ResponseUtil.BAD_REQUEST).body(null);
//        }
        return ResponseEntity.status((ResponseUtil.SUCCESS))
                .body(
                        promotionDomainList.stream().map(this::toPromotionOutDto).collect(Collectors.toList())
                );
    }

    @PostMapping("/lock/id/{id}")
    public ResponseEntity<Boolean> lockStock(@PathVariable("id") String id){
        //1. check promotion existing, stock may not accurate
        PromotionDomain promotionDomain = promotionApplicationService.getPromotionById(id);
        if (Objects.isNull(promotionDomain)){
            return ResponseEntity.status(ResponseUtil.BAD_REQUEST).body(false);
        }
        boolean isLocked = promotionStockCacheService.lockStock(id);
        return ResponseEntity.status(ResponseUtil.SUCCESS)
                .body(isLocked);
    }

    @PostMapping("/deduct/id/{id}")
    public ResponseEntity<Boolean> deductStock(@PathVariable("id") String id){
        //1. check promotion
        PromotionDomain promotionDomain = promotionService.getPromotionById(id);
        if (Objects.isNull(promotionDomain)){
            return ResponseEntity.status(ResponseUtil.BAD_REQUEST).body(false);
        }
        Boolean isDeducted = promotionService.deductStock(id);
        return ResponseEntity.status(ResponseUtil.SUCCESS)
                .body(isDeducted);
    }

    @PostMapping("/revert/id/{id}")
    public ResponseEntity<Boolean> revertStock(@PathVariable("id") String id){
        //1. check promotion
        PromotionDomain promotionDomain = promotionService.getPromotionById(id);
        if (Objects.isNull(promotionDomain)){
            return ResponseEntity.status(ResponseUtil.BAD_REQUEST).body(false);
        }
        Boolean isReverted = promotionService.revertStock(id);
        return ResponseEntity.status(ResponseUtil.SUCCESS)
                .body(isReverted);
    }

    private PromotionDomain toDomain(PromotionInDto promotionInDto){
        return PromotionDomain.builder()
                .promotionId(UUID.randomUUID().toString())
                .promotionName(promotionInDto.getPromotionName())
                .commodityId(promotionInDto.getCommodityId())
                .startTime(promotionInDto.getStartTime())
                .endTime(promotionInDto.getEndTime())
                .originalPrice(promotionInDto.getOriginalPrice())
                .promotionalPrice(promotionInDto.getPromotionalPrice())
                .totalStock(promotionInDto.getTotalStock())
                .availableStock(promotionInDto.getAvailableStock())
                .lockStock(promotionInDto.getLockStock())
                .imageUrl(promotionInDto.getImageUrl())
                .status(promotionInDto.getStatus())
                .build();
    }

    private PromotionOutDto toPromotionOutDto(PromotionDomain promotionDomain){
        return PromotionOutDto.builder()
                .promotionId(promotionDomain.getPromotionId())
                .promotionName(promotionDomain.getPromotionName())
                .commodityId(promotionDomain.getCommodityId())
                .startTime(promotionDomain.getStartTime())
                .endTime(promotionDomain.getEndTime())
                .originalPrice(promotionDomain.getOriginalPrice())
                .promotionalPrice(promotionDomain.getPromotionalPrice())
                .totalStock(promotionDomain.getTotalStock())
                .availableStock(promotionDomain.getAvailableStock())
                .lockStock(promotionDomain.getLockStock())
                .imageUrl(promotionDomain.getImageUrl())
                .status(promotionDomain.getStatus())
                .build();
    }
}
