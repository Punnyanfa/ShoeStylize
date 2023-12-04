package com.group7.tomcat.shoestylize.servlet.controller;

import com.group7.tomcat.shoestylize.servlet.entity.Shoe;
import com.group7.tomcat.shoestylize.servlet.entity.ShoeSize;
import com.group7.tomcat.shoestylize.servlet.sytem.ShoeManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpSession;

@WebServlet(name = "detail", urlPatterns = {"/details/*"})
public class DetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String requestURI = request.getRequestURI();
        HttpSession session = request.getSession();

        String[] part = requestURI.split("/");
        if (part.length == 4) {
            try {
                Shoe shoe = ShoeManager.getShoeById(Integer.parseInt(part[3]));
                if (shoe == null) {
                    request.getRequestDispatcher("/error_404.jsp").forward(request, response);
                    return;
                }
                session.setAttribute("Shoe", shoe);
                List<ShoeSize> size = ShoeManager.getSizesFromType(shoe.getType());
                request.setAttribute("Shoe_Size_M", size.stream().filter(p -> p.getGender().equals("M")).collect(Collectors.toList()));
                request.setAttribute("Shoe_Size_W", size.stream().filter(p -> p.getGender().equals("W")).collect(Collectors.toList()));
                request.setAttribute("Shoe_Gallery", ShoeManager.getShoeGallery(shoe.getId()));
                request.getRequestDispatcher("/details.jsp").forward(request, response);
            } catch (NumberFormatException e) {
                request.getRequestDispatcher("/error_404.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("/error_404.jsp").forward(request, response);
        }
    }

}
