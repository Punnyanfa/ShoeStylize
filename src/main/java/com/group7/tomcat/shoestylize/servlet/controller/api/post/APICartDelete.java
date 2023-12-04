package com.group7.tomcat.shoestylize.servlet.controller.api.post;

import com.group7.tomcat.shoestylize.servlet.sytem.GsonBuilder;
import com.group7.tomcat.shoestylize.servlet.sytem.OrderManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "api=cart-delete", urlPatterns = {"/api/carts/delete"})
public class APICartDelete extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        if (session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String uuid = request.getParameter("id-value");
        OrderManager.get().getBill(session).removeOrder(uuid);
        response.sendRedirect(request.getContextPath() + "/carts");

    }
}
