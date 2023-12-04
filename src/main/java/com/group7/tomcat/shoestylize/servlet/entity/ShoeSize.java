package com.group7.tomcat.shoestylize.servlet.entity;

import com.group7.tomcat.shoestylize.servlet.database.DBObject;

public class ShoeSize {

    private int id;
    private String sizeName;
    private String type;
    private String gender;
    
    public ShoeSize(DBObject db) {
        this.id = db.getInt("id");
        this.sizeName = db.getString("size_name");
        this.type = db.getString("type_id");
        this.gender = db.getString("gender");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGenderName() {
        if (gender.equals("M")) {
            return "Men";
        } else if (gender.equals("W")) {
            return "Women";
        }
        return "Other";
    }

    
    
}
