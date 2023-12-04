package com.group7.tomcat.shoestylize.servlet.controller.admin;

import com.group7.tomcat.shoestylize.servlet.dao.DAO;
import com.group7.tomcat.shoestylize.servlet.entity.Account;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpSession;

@WebServlet(name = "admin-users", urlPatterns = {"/admin/users"})
public class AdminServletUser extends HttpServlet {

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

        List<Account> users = new DAO().getAllUsers();
        request.setAttribute("USERS", users);
        
        request.getRequestDispatcher("/admin_users.jsp").forward(request, response);
    }
}
