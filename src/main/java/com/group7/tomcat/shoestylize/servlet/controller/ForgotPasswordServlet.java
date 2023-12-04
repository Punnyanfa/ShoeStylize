package com.group7.tomcat.shoestylize.servlet.controller;

import com.group7.tomcat.shoestylize.servlet.dao.DAO;
import com.group7.tomcat.shoestylize.servlet.entity.Account;
import com.group7.tomcat.shoestylize.servlet.sytem.EmailManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "forgot-password", urlPatterns = {"/forgot-password"})
public class ForgotPasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String token = request.getParameter("token");
        if (token == null) {
            request.getRequestDispatcher("/forgot-password.jsp").forward(request, response);
            return;
        }
        
        Account acc = new DAO().matchingToken(token);
        request.setAttribute("acc", acc);
        request.getRequestDispatcher("/forgot-password_success.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String email = request.getParameter("email");

        Account acc = new DAO().isEmailExist(email);
        if (acc == null) {
            return;
        }

        String content = "Please confirm your password here!\n\nClick here: <a href='http://localhost:8080/ShoeStylize_Tomcat/forgot-password?token=" + acc.getToken() + "'>to change your password</a>";

        EmailManager.sendMail(email, response, content);
        request.setAttribute("noti", "An email has been sent. Please check your email now.");
        request.getRequestDispatcher("/forgot-password.jsp").forward(request, response);
    }
}
