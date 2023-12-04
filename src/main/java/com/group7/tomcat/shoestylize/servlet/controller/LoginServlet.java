package com.group7.tomcat.shoestylize.servlet.controller;

import com.group7.tomcat.shoestylize.servlet.dao.DAO;
import com.group7.tomcat.shoestylize.servlet.entity.Account;
import com.group7.tomcat.shoestylize.servlet.sytem.OrderManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "login", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
        
        String previousPage = request.getHeader("Referer");
        HttpSession session = request.getSession();
        session.setAttribute("previousPage", previousPage);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        DAO dao = new DAO();
        Account acc = dao.getUser(username, password);
        if (acc != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", acc);
            OrderManager.get().assignBill(session);
            
            String previousPage = (String) session.getAttribute("previousPage");
            if (previousPage != null) {
                session.removeAttribute("previousPage");
                response.sendRedirect(previousPage);
            } else {
                response.sendRedirect(request.getContextPath() + "/home.jsp");
            }
        } else {
            request.setAttribute("error", "Either username or password is incorrect!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

}
