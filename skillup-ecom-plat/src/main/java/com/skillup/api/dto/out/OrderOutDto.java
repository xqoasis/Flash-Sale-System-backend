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
public class OrderOutDto {
    private Long orderNumber;

    //订单状态 0:预订单 -1:库存不足订单，1:已创建等待付款, 2表示已付款, 3订单过期或者无效
    private Integer orderStatus;

    private String promotionId;

    private String promotionName;

    private String userId;

    private Integer orderAmount;

    private LocalDateTime createTime;

    private LocalDateTime payTime;

}
