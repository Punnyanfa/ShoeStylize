package com.group7.tomcat.shoestylize.servlet.controller;

import com.group7.tomcat.shoestylize.servlet.entity.Bill;
import com.group7.tomcat.shoestylize.servlet.entity.PendingOrder;
import com.group7.tomcat.shoestylize.servlet.sytem.OrderManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpSession;

@WebServlet(name = "carts", urlPatterns = {"/carts"})
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        if (session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        List<PendingOrder> orders = OrderManager.get().getBill(session).getOrders();
        request.setAttribute("Orders", orders);
        request.getRequestDispatcher("/cart.jsp").forward(request, response);
    }
}
