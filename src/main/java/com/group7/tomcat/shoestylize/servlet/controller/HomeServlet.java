package com.group7.tomcat.shoestylize.servlet.controller;


import com.group7.tomcat.shoestylize.servlet.sytem.ShoeManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "home", urlPatterns = {"/home"})
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        request.setAttribute("Shoes", ShoeManager.getNewArrivalShoes());
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }
}
