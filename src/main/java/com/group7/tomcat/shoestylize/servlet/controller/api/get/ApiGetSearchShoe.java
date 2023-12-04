package com.group7.tomcat.shoestylize.servlet.controller.api.get;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.group7.tomcat.shoestylize.servlet.entity.Shoe;
import com.group7.tomcat.shoestylize.servlet.sytem.GsonBuilder;
import com.group7.tomcat.shoestylize.servlet.sytem.ShoeManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "api-search-shoes", urlPatterns = {"/api/search/shoes"})
public class ApiGetSearchShoe extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        String query = req.getParameter("query");

        List<Shoe> shoes = ShoeManager.getSearchShoesByQuery(query);
        JsonArray jArr = new JsonArray();

        for (Shoe shoe : shoes) {
            JsonObject shoeJson = new GsonBuilder()
                    .set("id", shoe.getId())
                    .set("image_link", shoe.getImageLink())
                    .set("title", shoe.getTitle())
                    .set("price", shoe.getPrice())
                    .buildJson();
            jArr.add(shoeJson);
        }

        resp.getWriter().println(new GsonBuilder().set("shoes", jArr).build());
    }
}
