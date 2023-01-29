/*
 * This file is generated by jOOQ.
 */
package com.skillup.infrastructure.jooq;


import com.skillup.infrastructure.jooq.tables.Commodity;
import com.skillup.infrastructure.jooq.tables.Orders;
import com.skillup.infrastructure.jooq.tables.Promotion;

import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling indexes of tables in skillup.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index PROMOTION_IDX_PROMOTION_COMMODITY_ID = Internal.createIndex(DSL.name("idx_promotion_commodity_id"), Promotion.PROMOTION, new OrderField[] { Promotion.PROMOTION.COMMODITY_ID }, false);
    public static final Index PROMOTION_IDX_PROMOTION_END_TIME = Internal.createIndex(DSL.name("idx_promotion_end_time"), Promotion.PROMOTION, new OrderField[] { Promotion.PROMOTION.END_TIME }, false);
    public static final Index PROMOTION_IDX_PROMOTION_START_TIME = Internal.createIndex(DSL.name("idx_promotion_start_time"), Promotion.PROMOTION, new OrderField[] { Promotion.PROMOTION.START_TIME }, false);
    public static final Index PROMOTION_IDX_PROMOTION_STATUS = Internal.createIndex(DSL.name("idx_promotion_status"), Promotion.PROMOTION, new OrderField[] { Promotion.PROMOTION.STATUS }, false);
    public static final Index ORDERS_IDX_USER_ID = Internal.createIndex(DSL.name("idx_user_id"), Orders.ORDERS, new OrderField[] { Orders.ORDERS.USER_ID }, false);
    public static final Index COMMODITY_UNI_COMMODITY_NAME = Internal.createIndex(DSL.name("uni_commodity_name"), Commodity.COMMODITY, new OrderField[] { Commodity.COMMODITY.COMMODITY_NAME }, false);
}
