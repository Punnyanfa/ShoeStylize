package com.group7.tomcat.shoestylize.servlet.sytem;

import com.group7.tomcat.shoestylize.servlet.database.DBContext;
import com.group7.tomcat.shoestylize.servlet.database.DBObject;
import com.group7.tomcat.shoestylize.servlet.entity.ShoeExtra;
import java.util.List;
import java.util.stream.Collectors;

public class ShoeExtraManager {
    
    public static List<ShoeExtra> getShoeExtras() {
        List<DBObject> dbobj = DBContext.executeQuery("SELECT * FROM \"Shoe_Extra\"");
        
        return dbobj.stream().map(ShoeExtra::new).collect(Collectors.toList());
    }

    public static ShoeExtra getShoeExtra(int id) {
        List<DBObject> dbobj = DBContext.executeQuery("SELECT * FROM \"Shoe_Extra\" WHERE id=?", id);
        if (dbobj.isEmpty()) {
            return null;
        }

        return new ShoeExtra(dbobj.get(0));
    }
}
