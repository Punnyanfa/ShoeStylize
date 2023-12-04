package com.group7.tomcat.shoestylize.servlet.controller.api.get;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.group7.tomcat.shoestylize.servlet.entity.ShoeExtra;
import com.group7.tomcat.shoestylize.servlet.sytem.GsonBuilder;
import com.group7.tomcat.shoestylize.servlet.sytem.ShoeExtraManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "api=shoe-extra-list", urlPatterns = {"/api/shoe-extra/list"})
public class APIShoeExtrasList extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        JsonArray jArr = new JsonArray();
        
        for (ShoeExtra extra : ShoeExtraManager.getShoeExtras()) {
            JsonObject builder = new GsonBuilder()
                    .set("id", extra.getId())
                    .set("title", extra.getTitle())
                    .set("description", extra.getDescription())
                    .set("image_link", extra.getImageLink())
                    .set("price", extra.getPrice())
                    .buildJson();
            
            jArr.add(builder);
        }
        
        response.getWriter().println(jArr.toString());
    }

}
