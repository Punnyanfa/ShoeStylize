package com.group7.tomcat.shoestylize.servlet.entity;

import com.group7.tomcat.shoestylize.servlet.database.DBObject;

public class ShoeGallery {

    private int id;
    private int shoeId;
    private String imageLink;
    
    public ShoeGallery(DBObject db) {
        this.id = db.getInt("id");
        this.shoeId = db.getInt("shoe_id");
        this.imageLink = db.getString("image_link");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShoeId() {
        return shoeId;
    }

    public void setShoeId(int shoeId) {
        this.shoeId = shoeId;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    
}
