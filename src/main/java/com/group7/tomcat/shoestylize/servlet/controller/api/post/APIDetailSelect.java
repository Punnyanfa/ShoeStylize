package com.group7.tomcat.shoestylize.servlet.controller.api.post;

import com.group7.tomcat.shoestylize.servlet.sytem.OrderManager;
import com.group7.tomcat.shoestylize.servlet.sytem.ShoeManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "api-detail-select", urlPatterns = {"/api/detail-select"})
public class APIDetailSelect extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        if (session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String idStr = request.getParameter("id");
        
        if (idStr == null) {
            response.sendRedirect(request.getContextPath() + "/explore");
            return;
        }
        
        try {
            session.setAttribute("StylizeForm_Step", 0);
            response.sendRedirect(request.getContextPath() + "/stylize-form?id=" + idStr);
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/detail/" + idStr);
        }

    }
}
