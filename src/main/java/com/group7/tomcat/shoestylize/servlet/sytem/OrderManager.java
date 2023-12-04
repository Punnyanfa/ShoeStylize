package com.group7.tomcat.shoestylize.servlet.sytem;

import com.group7.tomcat.shoestylize.servlet.database.DBContext;
import com.group7.tomcat.shoestylize.servlet.database.DBObject;
import com.group7.tomcat.shoestylize.servlet.entity.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderManager {

    private static OrderManager instance;

    public void assignBill(HttpSession session) {
        if (session.getAttribute("bill") == null) {
            session.setAttribute("bill", new PendingBill(session));
        }
    }

    public PendingOrder getPendingOrderByUUID(HttpSession session, String uuid) {
        PendingBill bill = getBill(session);
        if (bill == null) return null;

        return bill.getOrders().stream().filter(p -> p.getUUID().equals(uuid)).findFirst().orElse(null);
    }

    public PendingBill getBill(HttpSession session) {
        assignBill(session);
        return (PendingBill) session.getAttribute("bill");
    }

    public boolean submitOrder(PendingBill bill) {
        if (bill == null) {
            return false;
        }

        List<PendingOrder> orderList = bill.getOrders();
        if (orderList.isEmpty()) {
            return false;
        }

        List<DBObject> result = DBContext.executeQuery("INSERT INTO \"Bill\" (author_id) VALUES (?) RETURNING id", bill.getAuthor().getId());
        if (!result.isEmpty()) {
            int createdId = result.get(0).getInt("id");

            for (PendingOrder order : orderList) {

                List<DBObject> resultOrder;
                resultOrder = DBContext.executeQuery("INSERT INTO \"Order_Shoe\" (" +
                                "    shoe_id, image_link, bill_id, first_name, last_name, email," +
                                "    address1, address2, phone1, phone2, size_id, customer_note," +
                                "    reference_note, texture_data, total_price," +
                                "    order_payment_method, service_provider_id" +
                                ") " +
                                "VALUES ( " +
                                "    ?, ?, ?, ?, ?, ?, " +
                                "    ?, ?, ?, ?, ?, ?, " +
                                "    ?, ?, ?, ?, " +
                                "    ?" +
                                ") RETURNING id",
                        order.getShoeId(),
                        order.getTextureImageBytes(),
                        createdId,
                        order.getFirstName(),
                        order.getLastName(),
                        order.getEmail(),
                        order.getAddress1(),
                        order.getAddress2(),
                        order.getPhone1(),
                        order.getPhone2(),
                        order.getSizeId(),
                        order.getCustomerNote(),
                        order.getReferenceInput(),
                        order.getTextureData(),
                        order.getTotalPrice(),
                        "cod",
                        null
                );

                int createdOrderId = resultOrder.get(0).getInt("id");
                for (ShoeExtra extra : order.getExtras()) {
                    DBContext.executeUpdate("INSERT INTO \"Order_Shoe_Extra\"(shoe_extra_id, order_shoe_id) VALUES (?, ?)",
                            extra.getId(), createdOrderId);
                }
            }
            bill.getOrders().clear();
            return true;
        }
        return false;
    }

    public static OrderManager get() {
        if (instance == null) {
            instance = new OrderManager();
        }
        return instance;
    }

    public List<Order> getOrder() {
        List<DBObject> result = DBContext.executeQuery(
                "SELECT os.* AS order_id, s.id AS shoe_id, ss.id AS size_id, b.id AS bill_id, u.id AS user_id FROM \"Order_Shoe\" os "
                        + "INNER JOIN \"Shoe\" s ON os.shoe_id = s.id "
                        + "INNER JOIN \"Shoe_Size\" ss ON os.size_id = ss.id "
                        + "INNER JOIN \"Bill\" b ON os.bill_id = b.id "
                        + "INNER JOIN \"User\" u ON b.author_id = u.id");
        if (result.isEmpty()) {
            return new ArrayList<>();
        }

        return result.stream().map(p -> new Order(p)).collect(Collectors.toList());
    }

    public Order getImageByOrderId(int id) {
        List<DBObject> result = DBContext.executeQuery(
                "SELECT image_link FROM \"Order_Shoe\" WHERE id=?", id);
        return result.stream().map(p -> new Order(p)).findFirst().orElse(null);
    }

    public List<Order> getOrderByUser(int id) {
        List<DBObject> result = DBContext.executeQuery(
                "SELECT os.* AS order_id, s.id AS shoe_id, ss.id AS size_id, b.id AS bill_id, u.id AS user_id FROM \"Order_Shoe\" os "
                        + "INNER JOIN \"Shoe\" s ON os.shoe_id = s.id "
                        + "INNER JOIN \"Shoe_Size\" ss ON os.size_id = ss.id "
                        + "INNER JOIN \"Bill\" b ON os.bill_id = b.id "
                        + "INNER JOIN \"User\" u ON b.author_id = u.id " +
                        "WHERE u.id=? ORDER BY b.date_created DESC", id);
        if (result.isEmpty()) {
            return new ArrayList<>();
        }

        return result.stream().map(p -> new Order(p)).collect(Collectors.toList());
    }

    public Order getOrderById(int id) {
        List<DBObject> result = DBContext.executeQuery(
                "SELECT os.* AS order_id, s.id AS shoe_id, ss.id AS size_id, b.id AS bill_id, u.id AS user_id FROM \"Order_Shoe\" os "
                        + "INNER JOIN \"Shoe\" s ON os.shoe_id = s.id "
                        + "INNER JOIN \"Shoe_Size\" ss ON os.size_id = ss.id "
                        + "INNER JOIN \"Bill\" b ON os.bill_id = b.id "
                        + "INNER JOIN \"User\" u ON b.author_id = u.id " +
                        "WHERE os.id=? ORDER BY b.date_created DESC", id);
        return result.stream().map(p -> new Order(p)).findFirst().orElse(null);
    }
}
