package com.skillup.api.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommodityInDto {

    @NotNull (message = "commodity's name cannot be null")
    private String commodityName;
    private String description;
    @NotNull (message = "commodity's price cannot be null")
    private Integer price;
    private String imageUrl;

}
