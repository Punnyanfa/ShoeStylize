package com.group7.tomcat.shoestylize.servlet.entity;

import com.group7.tomcat.shoestylize.servlet.sytem.OrderManager;

import java.io.InputStream;
import java.text.DecimalFormat;

import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.util.ArrayList;
import java.util.List;

public class PendingBill {

    private final HttpSession session;
    private List<PendingOrder> orderList;

    public PendingBill(HttpSession session) {
        this.orderList = new ArrayList<>();
        this.session = session;
    }

    public Account getAuthor() {
        return (Account) session.getAttribute("user");
    }

    public void addOrder(int userId, int shoeId, String firstName, String lastName, String email,
                         String phone1, String phone2,
                         String address1, String address2,
                         String customerNote,
                         String textureData, InputStream texturePart,
                         int sizeId, Part referenceImage, String referenceInput, List<ShoeExtra> extras) {
        orderList.add(new PendingOrder(userId, shoeId, firstName, lastName, email,
                phone1, phone2,
                address1, address2,
                customerNote,
                textureData,
                texturePart,
                sizeId,
                referenceImage,
                referenceInput, extras));
    }

    public void removeOrder(String uuid) {
        orderList.removeIf(order -> order.getUUID().equals(uuid));
    }

    public List<PendingOrder> getOrders() {
        return orderList;
    }

    public double getTotalPrice(boolean hasFee) {
        double price = 0;
        for (PendingOrder order : orderList) {
            price += order.getTotalPrice();
        }

        if (hasFee) {
            price += getShippingFee();
        }
        double formattedPrice = Double.parseDouble(new DecimalFormat("0.00").format(price));
        return formattedPrice;
    }

    public void processBill() {
        if (getOrders().isEmpty()) return;
        OrderManager.get().submitOrder(this);
        getOrders().clear();
    }

    public double getShippingFee() {
        return 2.5;
    }
}
