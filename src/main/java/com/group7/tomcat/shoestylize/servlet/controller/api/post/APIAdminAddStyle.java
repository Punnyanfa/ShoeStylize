package com.group7.tomcat.shoestylize.servlet.controller.api.post;

import com.google.gson.JsonObject;
import com.group7.tomcat.shoestylize.servlet.database.DBContext;
import com.group7.tomcat.shoestylize.servlet.sytem.GsonBuilder;
import com.group7.tomcat.shoestylize.servlet.utils.GeneralUtils;
import com.group7.tomcat.shoestylize.servlet.utils.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "api-admin-add-style", urlPatterns = {"/api/admin/add-style"})
public class APIAdminAddStyle extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        JsonObject json = JsonUtils.parseJsonRequest(request);

        String title = json.get("title").getAsString();
        String type = json.get("type").getAsString();
        String description = json.get("description").getAsString();
        String priceStr = json.get("price").getAsString();
        String imageLink = json.get("image").getAsString();

        String errTitle = "";
        String errType = "";
        String errDesc = "";
        String errPrice = "";
        String errImage = "";

        if (GeneralUtils.checkTextExists(title)) {
            errTitle = "Title cannot be empty";
        }

        if (GeneralUtils.checkTextExists(type)) {
            errType = "Type cannot be empty";
        }

        if (GeneralUtils.checkTextExists(description)) {
            errDesc = "Description cannot be empty";
        }

        if (GeneralUtils.checkTextExists(priceStr)) {
            errPrice = "Price cannot be empty";
        }

        if (GeneralUtils.checkTextExists(imageLink)) {
            errImage = "Image URL cannot be empty";
        }

        double price = 0;
        try {
            price = Double.parseDouble(priceStr);
        } catch (Exception e) {
            errPrice = "Price must be a number or a decimal value";
        }

        if (!errTitle.isEmpty() || !errType.isEmpty() || !errDesc.isEmpty() || !errPrice.isEmpty() || !errImage.isEmpty()) {
            Map<String, String> map = new HashMap<>();
            map.put("title", errTitle);
            map.put("desc", errDesc);
            map.put("price", errPrice);
            map.put("image", errImage);
            map.put("type", errType);

            response.getWriter().println(new GsonBuilder().set("code", 400).set("error", map).build());
            return;
        }

        DBContext.executeUpdate("INSERT INTO \"Shoe\" " +
                        "(title, image_link, description, price, shoe_type) VALUES (?,?,?,?,?) "
                , title, imageLink, description, price, type);

        response.getWriter().println(new GsonBuilder().set("code", 201).build());
    }
}