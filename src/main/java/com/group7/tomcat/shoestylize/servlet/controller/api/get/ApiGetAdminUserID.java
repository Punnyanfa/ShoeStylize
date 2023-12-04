package com.group7.tomcat.shoestylize.servlet.controller.api.get;

import com.group7.tomcat.shoestylize.servlet.dao.DAO;
import com.group7.tomcat.shoestylize.servlet.entity.Account;
import com.group7.tomcat.shoestylize.servlet.sytem.GsonBuilder;
import com.group7.tomcat.shoestylize.servlet.utils.EncodeUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "api-get-admin-user-id", urlPatterns = {"/api/admin/user"})
public class ApiGetAdminUserID extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        String idStr = req.getParameter("id");
        int id = Integer.parseInt(idStr);

        Account acc = DAO.getUserById(id);

        if (acc != null) {
            String gsonBuilder = new GsonBuilder()
                    .set("id", acc.getId())
                    .set("username", acc.getUsername())
                    .set("first_name", EncodeUtils.encodeURI(acc.getFirstname()))
                    .set("last_name", EncodeUtils.encodeURI(acc.getLastname()))
                    .set("role", acc.getRole())
                    .set("email", acc.getEmail())
                    .set("login_method", acc.getLoginMethod())
                    .set("address", EncodeUtils.encodeURI(acc.getAddress()))
                    .set("city", EncodeUtils.encodeURI(acc.getCity()))
                    .set("state", EncodeUtils.encodeURI(acc.getState()))
                    .build();

            resp.getWriter().println(gsonBuilder);
        }
    }
}
