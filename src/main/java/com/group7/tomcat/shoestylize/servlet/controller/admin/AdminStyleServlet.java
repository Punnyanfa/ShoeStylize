package com.group7.tomcat.shoestylize.servlet.controller.admin;

import com.group7.tomcat.shoestylize.servlet.entity.Account;
import com.group7.tomcat.shoestylize.servlet.entity.News;
import com.group7.tomcat.shoestylize.servlet.entity.Shoe;
import com.group7.tomcat.shoestylize.servlet.sytem.NewsManager;
import com.group7.tomcat.shoestylize.servlet.sytem.ShoeManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpSession;

@WebServlet(name = "admin-style", urlPatterns = {"/admin/styles"})
public class AdminStyleServlet extends HttpServlet {

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

        List<Shoe> shoes = ShoeManager.getShoes();
        request.setAttribute("LIST_SHOES", shoes);
        request.getRequestDispatcher("/admin_styles.jsp").forward(request, response);
    }
}
