package com.group7.tomcat.shoestylize.servlet.entity;

import com.group7.tomcat.shoestylize.servlet.database.DBObject;
import com.group7.tomcat.shoestylize.servlet.utils.EncodeUtils;

import java.text.DecimalFormat;

public class Shoe {

    private int id;
    private String title;
    private String type;
    private String description;
    private double price;
    private String imageLink;

    public Shoe(int id, String title, String type, double price, String description) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.description = description;
        this.price = price;
    }

    public Shoe(DBObject obj) {
        this.id = obj.getInt("id");
        this.imageLink = obj.getString("image_link");
        this.title = obj.getString("title");
        this.type = obj.getString("shoe_type");
        this.description = obj.getString("description");
        this.price = obj.getDouble("price");

    }

    public String getImageLink() {
        return imageLink;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return EncodeUtils.decodeURI(description);
    }

    public double getPrice() {
        double formattedPrice = Double.parseDouble(new DecimalFormat("0.00").format(price));
        return formattedPrice;
    }

    public String getTemplateLink() {
        switch (type) {
            case "high-tops":
                return "/assets/shoe-template/high.png";
            case "low-tops":
                return "/assets/shoe-template/low.png";
            case "boots":
                return "/assets/shoe-template/boots.png";
            case "sandals":
                return "/assets/shoe-template/sandals.png";
            case "kid-shoes":
                return "/assets/shoe-template/kid.png";
            default:
                break;
        }
        return "";
    }
}
