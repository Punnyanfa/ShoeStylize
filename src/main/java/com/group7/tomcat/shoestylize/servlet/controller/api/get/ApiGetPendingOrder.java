package com.group7.tomcat.shoestylize.servlet.controller.api.get;

import com.google.gson.JsonArray;
import com.group7.tomcat.shoestylize.servlet.entity.PendingOrder;
import com.group7.tomcat.shoestylize.servlet.entity.ShoeExtra;
import com.group7.tomcat.shoestylize.servlet.sytem.GsonBuilder;
import com.group7.tomcat.shoestylize.servlet.sytem.OrderManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "api-get-pending-order", urlPatterns = {"/api/pending-order"})
public class ApiGetPendingOrder extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String uuid = req.getParameter("uuid");

        PendingOrder order = OrderManager.get().getPendingOrderByUUID(req.getSession(), uuid);
        if (order == null) {
            resp.getWriter().println("{}");
            return;
        }

        JsonArray jArr = new JsonArray();

        for (ShoeExtra extras : order.getExtras()) {
            jArr.add(new GsonBuilder().set("name", extras.getTitle()).set("price", extras.getPrice()).buildJson());
        }

        String json = new GsonBuilder()
                .set("title", order.getTitle())
                .set("textureData", order.getTextureData())
                .set("shoe_size_name", order.getSizeName())
                .set("address1", order.getAddress1())
                .set("address2", order.getAddress2())
                .set("phone1", order.getPhone1())
                .set("phone2", order.getPhone2())
                .set("note", order.getCustomerNote())
                .set("referencePrompt", order.getReferenceInput())
                .set("shoe_price", order.getPrice())
                .set("total_price", order.getTotalPrice())
                .set("extras", jArr)
                .build();

        resp.getWriter().println(json);

    }
}
