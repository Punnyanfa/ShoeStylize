package com.group7.tomcat.shoestylize.servlet.controller.admin;

import com.group7.tomcat.shoestylize.servlet.entity.Account;
import com.group7.tomcat.shoestylize.servlet.sytem.OrderManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;
import javax.servlet.http.HttpSession;

@WebServlet(name = "admin-orders", urlPatterns = {"/admin/orders"})
public class AdminOrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        if (session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        Account acc = (Account) session.getAttribute("user");
        if (!acc.getRole().equals("admin")) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        request.setAttribute("ORDERS", OrderManager.get().getOrder());
        request.setAttribute("ORDERS_PENDING", OrderManager.get().getOrder().stream()
                .filter(p -> p.getOrderShoeStatus().equals("pending"))
                .collect(Collectors.toList()));
        request.setAttribute("ORDERS_PROGRESSING", OrderManager.get().getOrder().stream()
                .filter(p -> p.getOrderShoeStatus().equals("in-progress"))
                .collect(Collectors.toList()));
        request.setAttribute("ORDERS_CANCELLED", OrderManager.get().getOrder().stream()
                .filter(p -> p.getOrderShoeStatus().equals("cancelled"))
                .collect(Collectors.toList()));
        request.setAttribute("ORDERS_SUCCESS", OrderManager.get().getOrder().stream()
                .filter(p -> p.getOrderShoeStatus().equals("success"))
                .collect(Collectors.toList()));
        request.getRequestDispatcher("/admin_orders.jsp").forward(request, response);
    }
}
