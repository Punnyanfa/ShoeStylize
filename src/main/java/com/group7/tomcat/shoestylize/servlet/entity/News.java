package com.group7.tomcat.shoestylize.servlet.entity;

import com.group7.tomcat.shoestylize.servlet.database.DBObject;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class News {

    private final int id;
    private final String title;
    private final String content;
    private final Date dateCreated;
    private final String username;

    public News(DBObject obj) {
        this.id = obj.getInt("id");
        this.title = obj.getString("title");
        this.content = obj.getString("content");
        this.dateCreated = obj.getDate("date_created");
        this.username = obj.getString("username");
    }

    public int getId() {
        return id;
    }
    
    public String getUsername() {
        return username;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
    
    

    public Date getDateCreated() {
        return dateCreated;
    }

    public String getFormatDateCreated() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(getDateCreated());
    }

    public String getFormatTimeCreated() {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        return sdf.format(getDateCreated());
    }

}
