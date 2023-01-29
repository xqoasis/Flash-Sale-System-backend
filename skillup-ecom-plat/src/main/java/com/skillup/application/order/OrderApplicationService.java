package com.skillup.application.order;

import com.skillup.domain.order.OrderDomain;
import com.skillup.domain.order.OrderService;
import com.skillup.domain.order.util.OrderStatus;
import com.skillup.domain.promotion.PromotionDomain;
import com.skillup.domain.promotion.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class OrderApplicationService {
    @Autowired
    PromotionService promotionService;
    @Autowired
    OrderService orderService;
    public OrderDomain createOrder(OrderDomain orderDomain) {
        //1. lock stock: call promotion service
        PromotionDomain promotionDomain = promotionService.getPromotionById(orderDomain.getPromotionId());
        if (Objects.isNull(promotionDomain)){
            orderDomain.setOrderStatus(OrderStatus.ITEM_ERROR);
            return orderDomain;
        }
        //2. then lock stock
        boolean isLocked = promotionService.lockStock(orderDomain.getPromotionId());
        if (!isLocked){
            orderDomain.setOrderStatus(OrderStatus.OUT_OF_STOCK);
            return orderDomain;
        }
        //2. create order time and set order status to "created"
        return orderService.creatOrder(orderDomain);
    }
}
