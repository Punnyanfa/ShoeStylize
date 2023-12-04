package com.group7.tomcat.shoestylize.servlet.controller.api.post;

import com.group7.tomcat.shoestylize.servlet.database.DBContext;
import com.group7.tomcat.shoestylize.servlet.entity.Account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "api-post-order-edit-cancel", urlPatterns = {"/api/order-edit/cancel"})
public class ApiPostOrderEditCancel extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        HttpSession session = req.getSession();
        Account user = (Account) session.getAttribute("user");

        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        try {
            String idStr = req.getParameter("id");
            int id = Integer.parseInt(idStr);

            DBContext.executeUpdate("UPDATE \"Order_Shoe\" SET order_shoe_status=? WHERE id=?",
                    "cancelled", id);
            resp.sendRedirect(req.getContextPath() + "/purchases");
        } catch (Exception e) {
            resp.sendRedirect(req.getContextPath() + "/purchases");
        }
    }
}
