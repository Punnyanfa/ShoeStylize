package com.group7.tomcat.shoestylize.servlet.entity;

import com.group7.tomcat.shoestylize.servlet.dao.DAO;
import com.group7.tomcat.shoestylize.servlet.database.DBContext;
import com.group7.tomcat.shoestylize.servlet.database.DBObject;
import com.group7.tomcat.shoestylize.servlet.sytem.ShoeExtraManager;
import com.group7.tomcat.shoestylize.servlet.sytem.ShoeManager;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Order {

    private final Shoe shoe;
    private final ShoeSize size;
    private final Account user;
    private final String phone1;
    private final String phone2;
    private final String address1;
    private final String address2;
    private final String customerNote;
    private final List<ShoeExtra> extras;
    private final int orderId;
    private final String orderShoeStatus;
    private final String referencePrompt;
    private final String textureData;
    private final String serviceProviderNote;
    private final byte[] textureImage;
    private final Date dateCreated;

    public Order(DBObject obj) {
        int shoeId = obj.getInt("shoe_id");
        int sizeId = obj.getInt("size_id");
        int userId = obj.getInt("user_id");
        int billId = obj.getInt("bill_id");

        this.orderId = obj.getInt("id");
        this.shoe = ShoeManager.getShoeById(shoeId);
        this.size = ShoeManager.getSizeFromId(sizeId);
        this.user = DAO.getUserById(userId);
        this.phone1 = obj.getString("phone1");
        this.phone2 = obj.getString("phone2");
        this.address1 = obj.getString("address1");
        this.address2 = obj.getString("address2");
        this.customerNote = obj.getString("customer_note");
        this.orderShoeStatus = obj.getString("order_shoe_status");
        this.referencePrompt = obj.getString("reference_note");
        this.textureData = obj.getString("texture_data");
        this.textureImage = obj.getBytes("image_link");
        this.dateCreated = obj.getDate("date_created");
        this.serviceProviderNote = obj.getString("service_provider_note");

        List<DBObject> extraObj = DBContext.executeQuery("SELECT * FROM \"Order_Shoe_Extra\" WHERE order_shoe_id=?", orderId);
        this.extras = extraObj.stream().map(p -> ShoeExtraManager.getShoeExtra(p.getInt("shoe_extra_id"))).collect(Collectors.toList());
    }

    public int getId() {
        return orderId;
    }

    public double getTotalPrice() {
        double basePrice = shoe.getPrice();
        for (ShoeExtra extra : getExtras()) {
            basePrice += extra.getPrice();
        }
        basePrice += 2.5;
        double formattedPrice = Double.parseDouble(new DecimalFormat("0.00").format(basePrice));
        return formattedPrice;
    }

    public Shoe getShoe() {
        return shoe;
    }

    public ShoeSize getSize() {
        return size;
    }

    public Account getUser() {
        return user;
    }

    public String getPhone1() {
        return phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public String getCustomerNote() {
        return customerNote;
    }

    public List<ShoeExtra> getExtras() {
        return extras;
    }

    public String getOrderShoeStatus() {
        return orderShoeStatus;
    }

    public String getReferencePrompt() {
        return referencePrompt;
    }

    public String getTextureData() {
        return textureData;
    }

    public String getServiceProviderNote() {
        return serviceProviderNote;
    }

    public byte[] getTextureImage() {
        return textureImage;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public String getFormatDate() {
        Date date = getDateCreated();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        return sdf.format(date);
    }
}
