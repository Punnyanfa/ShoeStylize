package com.group7.tomcat.shoestylize.servlet.entity;

import com.group7.tomcat.shoestylize.servlet.database.DBContext;
import com.group7.tomcat.shoestylize.servlet.database.DBObject;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Bill {

    private final List<Order> orders = new ArrayList<>();
    private final int id;
    private final int author_id;
    private final Date dateCreated;
    private double totalPrice = 0;

    public Bill(DBObject obj) {
        this.id = obj.getInt("id");
        this.author_id = obj.getInt("author_id");
        this.dateCreated = obj.getDate("date_created");

        List<DBObject> dbOrders = DBContext.executeQuery(
                "SELECT os.*, s.title, s.price, ss.size_name, s.image_link, s.id AS shoe_id, s.image_link AS preview_link, u.username " +
                        "FROM \"Order_Shoe\" os " +
                        "INNER JOIN \"Shoe\" s ON os.shoe_id = s.id " +
                        "INNER JOIN \"Shoe_Size\" ss ON os.size_id = ss.id " +
                        "INNER JOIN \"Bill\" b ON os.bill_id = b.id " +
                        "INNER JOIN \"User\" u ON b.author_id = u.id " +
                        "WHERE os.bill_id=?", id);

        dbOrders.forEach(p -> {
            orders.add(new Order(p));
        });

        for (Order order : orders) {
            totalPrice += order.getTotalPrice();
        }
    }

    public List<Order> getOrders() {
        return orders;
    }

    public int getId() {
        return id;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public String getFormatDateCreated() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy 'at' hh:mm:ss");
        return sdf.format(getDateCreated());
    }

    public double getTotalPrice() {
        double formattedPrice = Double.parseDouble(new DecimalFormat("0.00").format(totalPrice));
        return formattedPrice;
    }

    public double getTotalPrice(boolean b) {
        double formattedPrice = Double.parseDouble(new DecimalFormat("0.00").format(totalPrice));
        if (b) {
            return formattedPrice + 2.5;
        }
        return formattedPrice;
    }
}
