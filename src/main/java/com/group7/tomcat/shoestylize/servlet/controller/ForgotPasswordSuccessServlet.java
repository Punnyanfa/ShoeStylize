package com.group7.tomcat.shoestylize.servlet.controller;

import com.group7.tomcat.shoestylize.servlet.dao.DAO;
import com.group7.tomcat.shoestylize.servlet.database.DBContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.http.HttpSession;

@WebServlet(name = "forgot-password-success", urlPatterns = {"/forgot-password/recover"})
public class ForgotPasswordSuccessServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String token = request.getParameter("token");
        String pwd = request.getParameter("password");
        String repwd = request.getParameter("repassword");

        request.setAttribute("token", token);
        request.setAttribute("pwd", pwd);
        request.setAttribute("repwd", repwd);

        if (token == null) {
            request.setAttribute("error", "Credential not valid!");
            request.getRequestDispatcher("/forgot-password_success.jsp").forward(request, response);
            return;
        }

        if (pwd.length() < 8 || pwd.length() > 40) {
            request.setAttribute("error", "New password must be between 8-40 chars!");
            request.getRequestDispatcher("/forgot-password_success.jsp").forward(request, response);
            return;
        }

        if (!repwd.equals(pwd)) {
            request.setAttribute("error", "Passwords do not match!");
            request.getRequestDispatcher("/forgot-password_success.jsp").forward(request, response);
            return;
        }

        DBContext.executeUpdate("UPDATE [User] SET password=? WHERE token=?", pwd, token);
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            session.setAttribute("user", new DAO().matchingToken(token));
        }
        
        request.setAttribute("success", "Changing password successfully!");
        request.getRequestDispatcher("/forgot-password_success.jsp").forward(request, response);
    }
}
