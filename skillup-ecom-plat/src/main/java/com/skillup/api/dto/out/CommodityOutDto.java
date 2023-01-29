package com.skillup.api.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommodityOutDto {
    private String commodityId;
    private String commodityName;
    private String description;
    private Integer price;
    private String imageUrl;

}
