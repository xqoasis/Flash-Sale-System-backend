/*
 * This file is generated by jOOQ.
 */
package com.skillup.infrastructure.jooq;


import com.skillup.infrastructure.jooq.tables.Commodity;
import com.skillup.infrastructure.jooq.tables.Employee;
import com.skillup.infrastructure.jooq.tables.Orders;
import com.skillup.infrastructure.jooq.tables.Promotion;
import com.skillup.infrastructure.jooq.tables.User;

import java.util.Arrays;
import java.util.List;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Skillup extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>skillup</code>
     */
    public static final Skillup SKILLUP = new Skillup();

    /**
     * The table <code>skillup.commodity</code>.
     */
    public final Commodity COMMODITY = Commodity.COMMODITY;

    /**
     * The table <code>skillup.employee</code>.
     */
    public final Employee EMPLOYEE = Employee.EMPLOYEE;

    /**
     * The table <code>skillup.orders</code>.
     */
    public final Orders ORDERS = Orders.ORDERS;

    /**
     * The table <code>skillup.promotion</code>.
     */
    public final Promotion PROMOTION = Promotion.PROMOTION;

    /**
     * The table <code>skillup.user</code>.
     */
    public final User USER = User.USER;

    /**
     * No further instances allowed
     */
    private Skillup() {
        super("skillup", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.<Table<?>>asList(
            Commodity.COMMODITY,
            Employee.EMPLOYEE,
            Orders.ORDERS,
            Promotion.PROMOTION,
            User.USER);
    }
}
