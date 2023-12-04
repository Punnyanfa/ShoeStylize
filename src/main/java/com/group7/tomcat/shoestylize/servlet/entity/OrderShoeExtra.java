package com.group7.tomcat.shoestylize.servlet.entity;

import com.group7.tomcat.shoestylize.servlet.database.DBObject;

public class OrderShoeExtra {

    private final int id;
    private final int shoeExtraId;
    private final int orderShoeId;

    public OrderShoeExtra(DBObject obj) {
        this.id = obj.getInt("id");
        this.shoeExtraId = obj.getInt("shoe_extra_id");
        this.orderShoeId = obj.getInt("order_shoe_id");
    }

    public int getId() {
        return id;
    }

    public int getShoeExtraId() {
        return shoeExtraId;
    }

    public int getOrderShoeId() {
        return orderShoeId;
    }
}
