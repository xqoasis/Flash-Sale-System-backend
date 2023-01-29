package com.skillup.domain.order.util;

import java.util.HashMap;
import java.util.Map;

public enum OrderStatus {
    ITEM_ERROR(-2),
    OUT_OF_STOCK(-1),
    READY(0),
    CREATED(1),
    PAYED(2),
    OVERTIME(3),
    ;
    public Integer code;

    //小型缓存
    public static final Map<Integer, OrderStatus> cachedStatus = new HashMap<Integer, OrderStatus>(){
        {
            put(-2, ITEM_ERROR);
            put(-1, OUT_OF_STOCK);
            put(0, READY);
            put(1, CREATED);
            put(2, PAYED);
            put(3, OVERTIME);
        }
    };

    OrderStatus(int code){
        this.code = code;
    }
}
