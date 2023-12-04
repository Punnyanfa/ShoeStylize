package com.group7.tomcat.shoestylize.servlet.controller.api.get;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.group7.tomcat.shoestylize.servlet.dao.DAO;
import com.group7.tomcat.shoestylize.servlet.entity.Account;
import com.group7.tomcat.shoestylize.servlet.sytem.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "api-get-admin-users", urlPatterns = {"/api/admin/users"})
public class ApiGetAdminUsers extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        List<Account> accs = new DAO().getAllUsers();
        JsonArray jArr = new JsonArray();

        for (Account acc : accs) {
            JsonObject gsonBuilder = new GsonBuilder()
                    .set("id", acc.getId())
                    .set("username", acc.getUsername())
                    .set("first_name", acc.getFirstname())
                    .set("last_name", acc.getLastname())
                    .set("role", acc.getRole())
                    .set("email", acc.getEmail())
                    .set("login_method", acc.getLoginMethod())
                    .set("address", acc.getAddress())
                    .set("city", acc.getCity())
                    .set("state", acc.getState())
                    .buildJson();
            jArr.add(gsonBuilder);
        }

        String data = new GsonBuilder()
                .set("code", 200)
                .set("data", jArr).build();

        resp.getWriter().println(data);
    }
}
