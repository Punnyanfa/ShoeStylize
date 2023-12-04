package com.group7.tomcat.shoestylize.servlet.controller;

import com.group7.tomcat.shoestylize.servlet.entity.Shoe;
import com.group7.tomcat.shoestylize.servlet.sytem.ShoeExtraManager;
import com.group7.tomcat.shoestylize.servlet.sytem.ShoeManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.http.HttpSession;

@WebServlet(name = "stylize-form", urlPatterns = {"/stylize-form"})
public class StylizeFormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();

        if (session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String id = request.getParameter("id");
        if (id == null) {
            request.getRequestDispatcher("/error_404.jsp").forward(request, response);
            return;
        }
        
        String size = request.getParameter("size");
        if(size != null) {
            try {
                int sizeId = Integer.parseInt(size);
                request.setAttribute("Shoe_size_selected", sizeId);
            } catch (NumberFormatException e) {
                // Xử lý nếu không thể chuyển đổi thành số nguyên
                e.printStackTrace(); // Hoặc xử lý lỗi theo cách khác
            }
        }

        try {
            Shoe shoe = ShoeManager.getShoeById(Integer.parseInt(id));
            if (shoe == null) {
                request.getRequestDispatcher("/error_404.jsp").forward(request, response);
                return;
            }

            request.setAttribute("Shoe_Size", ShoeManager.getSizesFromType(shoe.getType()));
            request.setAttribute("ShoeExtras", ShoeExtraManager.getShoeExtras());
            request.getRequestDispatcher("/stylize-form.jsp").forward(request, response);

        } catch (IOException | NumberFormatException | ServletException e) {
            request.getRequestDispatcher("/error_404.jsp").forward(request, response);
        }
    }
}
