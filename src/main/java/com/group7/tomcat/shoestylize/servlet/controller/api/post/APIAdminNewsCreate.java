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

@WebServlet(name = "api-admin-news-create", urlPatterns = {"/api/admin/news/create"})
public class APIAdminNewsCreate extends HttpServlet {

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
        
        String title = request.getParameter("news_title");
        String content = request.getParameter("news_content");
        
        if (title == null || title.isEmpty() || title.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/admin/news"); 
        }
        
        NewsManager.createNews(session, title, content);
        response.sendRedirect(request.getContextPath() + "/admin/news");

    }
}
