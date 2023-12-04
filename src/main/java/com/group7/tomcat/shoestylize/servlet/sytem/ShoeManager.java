package com.group7.tomcat.shoestylize.servlet.sytem;

import com.group7.tomcat.shoestylize.servlet.database.DBContext;
import com.group7.tomcat.shoestylize.servlet.database.DBObject;
import com.group7.tomcat.shoestylize.servlet.entity.Shoe;
import com.group7.tomcat.shoestylize.servlet.entity.ShoeGallery;
import com.group7.tomcat.shoestylize.servlet.entity.ShoeSize;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ShoeManager {

    public static List<Shoe> getShoes() {
        List<DBObject> db = DBContext.executeQuery("SELECT * FROM \"Shoe\"");
        if (db.isEmpty()) {
            return new ArrayList<>();
        }

        return db.stream().map(p -> new Shoe(p)).collect(Collectors.toList());
    }

    public static List<Shoe> getSearchShoesByQuery(String query) {
        if (query == null || query.length() < 2) return new ArrayList<>();

        List<DBObject> db = DBContext.executeQuery("SELECT * FROM \"Shoe\" WHERE title ILIKE ? LIMIT 6", "%" + query + "%");
        if (db.isEmpty()) {
            return new ArrayList<>();
        }

        return db.stream().map(p -> new Shoe(p)).collect(Collectors.toList());
    }

    public static List<Shoe> getNewArrivalShoes() {
        List<DBObject> db = DBContext.executeQuery("SELECT * FROM \"Shoe\" ORDER BY date_created DESC LIMIT 6");
        if (db.isEmpty()) {
            return new ArrayList<>();
        }

        return db.stream().map(p -> new Shoe(p)).collect(Collectors.toList());
    }

    public static Shoe getShoeById(int id) {
        List<DBObject> db = DBContext.executeQuery("SELECT * FROM \"Shoe\" WHERE id = ?", id);
        if (db.isEmpty()) {
            return null;
        }

        return new Shoe(db.get(0));
    }

    public static List<ShoeSize> getSizesFromType(String type) {
        List<DBObject> db = DBContext.executeQuery("SELECT * FROM \"Shoe_Size\" WHERE type_id=?", type);
        if (db.isEmpty()) {
            return new ArrayList<>();
        }

        return db.stream().map(p -> new ShoeSize(p)).collect(Collectors.toList());
    }

    public static ShoeSize getSizeFromId(int id) {
        List<DBObject> data = DBContext.executeQuery("SELECT * FROM \"Shoe_Size\" WHERE id=?", id);
        if (data.isEmpty()) {
            return null;
        }

        return new ShoeSize(data.get(0));
    }

    public static List<ShoeGallery> getShoeGallery(int id) {
        List<DBObject> db = DBContext.executeQuery("SELECT * FROM \"Shoe_Gallery\" WHERE shoe_id=?", id);
        if (db.isEmpty()) {
            return new ArrayList<>();
        }

        return db.stream().map(p -> new ShoeGallery(p)).collect(Collectors.toList());
    }
}
