package com.group7.tomcat.shoestylize.servlet.controller;

import com.group7.tomcat.shoestylize.servlet.entity.Account;
import com.group7.tomcat.shoestylize.servlet.entity.Bill;
import com.group7.tomcat.shoestylize.servlet.entity.Order;
import com.group7.tomcat.shoestylize.servlet.entity.PendingOrder;
import com.group7.tomcat.shoestylize.servlet.sytem.OrderManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "purchases", urlPatterns = {"/purchases"})
public class PurchasesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Account user = (Account) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        List<Order> orders = OrderManager.get().getOrderByUser(user.getId());
        request.setAttribute("LIST_ALL_ORDERS", orders);
        request.setAttribute("LIST_PENDING_ORDERS", orders.stream().filter(p -> p.getOrderShoeStatus().equals("pending")).collect(Collectors.toList()));
        request.setAttribute("LIST_PROGRESS_ORDERS", orders.stream().filter(p -> p.getOrderShoeStatus().equals("in_progress")).collect(Collectors.toList()));
        request.setAttribute("LIST_COMPLETED_ORDERS", orders.stream().filter(p -> p.getOrderShoeStatus().equals("success")).collect(Collectors.toList()));
        request.setAttribute("LIST_CANCELLED_ORDERS", orders.stream().filter(p -> p.getOrderShoeStatus().equals("cancelled")).collect(Collectors.toList()));

        request.getRequestDispatcher("/purchases.jsp").forward(request, response);
    }
}
