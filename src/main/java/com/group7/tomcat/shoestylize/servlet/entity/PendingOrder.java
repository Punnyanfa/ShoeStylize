package com.group7.tomcat.shoestylize.servlet.entity;

import com.group7.tomcat.shoestylize.servlet.database.DBContext;
import com.group7.tomcat.shoestylize.servlet.database.DBObject;
import com.group7.tomcat.shoestylize.servlet.sytem.ShoeManager;
import com.group7.tomcat.shoestylize.servlet.utils.IDUtils;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.List;

public class PendingOrder {

    private final String uuid;
    private final int userId;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phone1;
    private final String phone2;
    private final String customerNote;
    private final String textureData;
    private final InputStream textureImage;
    private byte[] textureImageBytes;
    private final int sizeId;
    private final String referenceInput;
    private final int shoeId;
    private final DBObject dbObject;
    private final List<ShoeExtra> extras;
    private final String address1;
    private final String address2;
    private final ShoeSize shoeSize;
    private final String shoeImage;
    private final String shoeTitle;

    public PendingOrder(
            int userId,
            int shoeId,
            String firstName,
            String lastName,
            String email,
            String phone1,
            String phone2,
            String address1,
            String address2,
            String customerNote,
            String textureData,
            InputStream texturePart,
            int sizeId,
            Part referenceImage,
            String referenceInput,
            List<ShoeExtra> extras) {
        this.shoeId = shoeId;
        this.uuid = IDUtils.generateUUID();
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.address1 = address1;
        this.address2 = address2;
        this.customerNote = customerNote;
        this.textureData = textureData;
        this.textureImage = texturePart;
        try {
            this.textureImageBytes = IOUtils.toByteArray(textureImage);
        } catch (IOException e) {
            this.textureImageBytes = new byte[1];
        }
        this.sizeId = sizeId;
        this.referenceInput = referenceInput;
        this.extras = extras;

        this.dbObject = DBContext.executeQuery("SELECT * FROM \"Shoe\" WHERE id=?", shoeId).get(0);
        this.shoeSize = ShoeManager.getSizeFromId(sizeId);
        this.shoeTitle = dbObject.getString("title");
        this.shoeImage = dbObject.getString("image_link");
    }

    public String getUUID() {
        return uuid;
    }

    public String getTitle() {
        return dbObject.getString("title");
    }

    public double getPrice() {
        double formattedPrice = Double.parseDouble(new DecimalFormat("0.00").format(dbObject.getDouble("price")));
        return formattedPrice;
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

    public String getSizeGender() {
        return shoeSize.getGender();
    }

    public int getSizeId() {
        return sizeId;
    }

    public String getSizeName() {
        return shoeSize.getSizeName();
    }

    public DBObject getDbObject() {
        return dbObject;
    }

    public int getShoeId() {
        return shoeId;
    }

    public List<ShoeExtra> getExtras() {
        return extras;
    }

    public double getTotalPrice() {
        double basePrice = getPrice();
        for (ShoeExtra extra : getExtras()) {
            basePrice += extra.getPrice();
        }
        double formattedPrice = Double.parseDouble(new DecimalFormat("0.00").format(basePrice));
        return formattedPrice;
    }

    public int getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getCustomerNote() {
        return customerNote;
    }

    public String getTextureData() {
        return textureData;
    }

    public InputStream getTextureImage() {
        return textureImage;
    }

    public byte[] getTextureImageBytes() {
        return textureImageBytes;
    }

    public String getReferenceInput() {
        return referenceInput;
    }

    public String getShoeImage() {
        return shoeImage;
    }

    public String getShoeTitle() {
        return shoeTitle;
    }
}
