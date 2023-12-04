package com.group7.tomcat.shoestylize.servlet.controller.api.post;

import com.group7.tomcat.shoestylize.servlet.database.DBContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "api-post-admin-users", urlPatterns = {"/api/admin/users-update"})
public class ApiPostAdminUsers extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        String idStr = req.getParameter("id");
        int id = Integer.parseInt(idStr);

        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        String state = req.getParameter("state");
        String city = req.getParameter("city");

        DBContext.executeUpdate("UPDATE \"User\" SET " +
                        "firstname=?, lastname=?, email=?, address=?, state=?, city=? WHERE id=?",
                firstName, lastName, email, address, state, city, id);

        resp.sendRedirect(req.getContextPath() + "/admin/users");
    }
}
