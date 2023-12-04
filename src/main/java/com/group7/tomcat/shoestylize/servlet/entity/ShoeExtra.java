package com.group7.tomcat.shoestylize.servlet.entity;

import com.group7.tomcat.shoestylize.servlet.database.DBObject;
import java.text.DecimalFormat;

public class ShoeExtra {

    private final int id;
    private final String title;
    private final String description;
    private final double price;
    private final String imageLink;

    public ShoeExtra(DBObject obj) {
        this.id = obj.getInt("id");
        this.imageLink = obj.getString("image_link");
        this.title = obj.getString("title");
        this.description = obj.getString("description");
        this.price = obj.getDouble("price");
    }
    
    public String getImageLink() {
        return imageLink;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        double formattedPrice = Double.parseDouble(new DecimalFormat("0.00").format(price));
        return formattedPrice;
    }
}
