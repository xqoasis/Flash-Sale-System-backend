package com.skillup.api;

import com.skillup.api.dto.in.OrderInDto;
import com.skillup.api.dto.out.OrderOutDto;
import com.skillup.api.util.ResponseUtil;
import com.skillup.api.util.SnowFlake;
import com.skillup.application.order.OrderApplicationService;
import com.skillup.domain.order.OrderDomain;
import com.skillup.domain.order.util.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    SnowFlake snowFlake;

    @Autowired
    OrderApplicationService orderApplicationService;


    @PostMapping
    public ResponseEntity<OrderOutDto> createOrder(@RequestBody OrderInDto orderInDto){
        OrderDomain createdOrder = orderApplicationService.createOrder(toDomain(orderInDto));
        if (createdOrder.getOrderStatus().equals(OrderStatus.CREATED)){
            return ResponseEntity.status(ResponseUtil.SUCCESS).body(toOrderOutDto(createdOrder));
        }
        return ResponseEntity.status(ResponseUtil.INTERNAL_ERROR).body(toOrderOutDto(createdOrder));
    }

    private OrderDomain toDomain(OrderInDto orderInDto) {
        return OrderDomain.builder()
                .orderNumber(snowFlake.nextId())
                .userId(orderInDto.getUserId())
                .promotionId(orderInDto.getPromotionId())
                .promotionName(orderInDto.getPromotionName())
                .orderAmount(orderInDto.getOrderAmount())
                .orderStatus(OrderStatus.READY)
                .build();
    }

    private OrderOutDto toOrderOutDto(OrderDomain orderDomain) {
        return OrderOutDto.builder()
                .orderNumber(orderDomain.getOrderNumber())
                .userId(orderDomain.getUserId())
                .promotionId(orderDomain.getPromotionId())
                .promotionName(orderDomain.getPromotionName())
                .orderAmount(orderDomain.getOrderAmount())
                .orderStatus(orderDomain.getOrderStatus().code)
                .createTime(orderDomain.getCreateTime())
                .payTime(orderDomain.getPayTime())
                .build();
    }

}
