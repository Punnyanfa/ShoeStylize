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

@WebServlet(name = "api-sp-status", urlPatterns = {"/api/sp/status"})
public class APIServiceProviderStatus extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        if (session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        Account acc = (Account) session.getAttribute("user");

        String id = request.getParameter("id");
        String status = request.getParameter("status");
        if (id == null || status == null) {
            response.sendRedirect(request.getContextPath() + "/sp/orders");
            return;
        }

        if (status.equals("success") || status.equals("cancelled")) {
            try {
                int i = Integer.parseInt(id);
                DBContext.executeUpdate("UPDATE \"Order_Shoe\" SET order_shoe_status=? WHERE id=? AND service_provider_id=?",
                        status, i, acc.getId());
                response.sendRedirect(request.getContextPath() + "/sp/orders");
            } catch (IOException | NumberFormatException e) {
                response.sendRedirect(request.getContextPath() + "/sp/orders");
            }
            return;
        }

        if (status.equals("unassign")) {
            try {
                int i = Integer.parseInt(id);
                DBContext.executeUpdate("UPDATE \"Order_Shoe\" SET order_shoe_status=?, service_provider_id=NULL WHERE id=? AND service_provider_id=?",
                        "pending", i, acc.getId());
                response.sendRedirect(request.getContextPath() + "/sp/orders");
            } catch (IOException | NumberFormatException e) {
                response.sendRedirect(request.getContextPath() + "/sp/orders");
            }
            return;
        }

        response.sendRedirect(request.getContextPath() + "/sp/orders");
    }
}
