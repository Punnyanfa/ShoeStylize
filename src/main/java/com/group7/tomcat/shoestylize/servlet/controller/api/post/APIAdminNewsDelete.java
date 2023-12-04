package com.group7.tomcat.shoestylize.servlet.controller.api.post;

import com.group7.tomcat.shoestylize.servlet.entity.Account;
import com.group7.tomcat.shoestylize.servlet.sytem.NewsManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "api-admin-news-delete", urlPatterns = {"/api/admin/news/delete"})
public class APIAdminNewsDelete extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        if (session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        Account acc = (Account) session.getAttribute("user");
        if (!acc.getRole().equals("admin")) {
            response.sendRedirect(request.getContextPath() + "/home");
        }
        
        String idStr = request.getParameter("id");

        if (idStr != null) {
            try {
                NewsManager.deleteNews(session, Integer.parseInt(idStr));
            } catch (Exception e) {
                response.sendRedirect(request.getContextPath() + "/admin/news");
            }
        }
        
        response.sendRedirect(request.getContextPath() + "/admin/news");

    }
}
