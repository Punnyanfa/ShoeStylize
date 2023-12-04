package com.group7.tomcat.shoestylize.servlet.sytem;

import com.group7.tomcat.shoestylize.servlet.database.DBContext;
import com.group7.tomcat.shoestylize.servlet.database.DBObject;
import com.group7.tomcat.shoestylize.servlet.entity.Account;
import com.group7.tomcat.shoestylize.servlet.entity.News;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpSession;

public class NewsManager {

    public static List<News> getNews() {
        List<DBObject> dbObj = DBContext.executeQuery("SELECT n.*, u.username FROM \"News\" n INNER JOIN \"User\" u ON n.author_id = u.id ORDER BY date_created DESC");
        return dbObj.stream().map(p -> new News(p)).collect(Collectors.toList());
    }

    public static News getNewsById(int id) {
        List<DBObject> dbObj = DBContext.executeQuery("SELECT n.*, u.username FROM \"News\" n INNER JOIN \"User\" u ON n.author_id = u.id WHERE n.id = ? ORDER BY date_created DESC", id);
        return dbObj.stream().map(p -> new News(p)).findFirst().orElse(null);
    }

    public static List<News> getNewsRecommend() {
        List<DBObject> dbObj = DBContext.executeQuery("SELECT n.*, u.username FROM \"News\" n INNER JOIN \"User\" u ON n.author_id = u.id ORDER BY date_created DESC LIMIT 6");
        return dbObj.stream().map(p -> new News(p)).collect(Collectors.toList());
    }
    
    public static void createNews(HttpSession session, String title, String content) {
        Account acc = (Account) session.getAttribute("user");
        if (acc == null || !acc.getRole().equals("admin")) {
            return;
        }
        
        DBContext.executeUpdate("INSERT INTO \"News\" (title, content, author_id) VALUES (?,?,?)", title, content, acc.getId());
    }
    
    public static void deleteNews(HttpSession session, int id) {
        Account acc = (Account) session.getAttribute("user");
        if (acc == null || !acc.getRole().equals("admin")) {
            return;
        }
        
        DBContext.executeUpdate("DELETE FROM \"News\" WHERE id=?", id);
    } 
}
