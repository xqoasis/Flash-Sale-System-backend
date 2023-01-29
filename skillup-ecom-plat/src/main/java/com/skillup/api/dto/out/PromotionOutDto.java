package com.skillup.api.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PromotionOutDto {
    String promotionId;
    String promotionName;
    String commodityId;
    Integer originalPrice;
    Integer promotionalPrice;
    LocalDateTime startTime;
    LocalDateTime endTime;
    Integer status;
    Long totalStock;
    Long availableStock;
    Long lockStock;
    String imageUrl;
}
