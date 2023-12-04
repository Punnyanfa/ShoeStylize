package com.group7.tomcat.shoestylize.servlet.controller;

import com.group7.tomcat.shoestylize.servlet.dao.DAO;
import com.group7.tomcat.shoestylize.servlet.utils.RestrictUtils;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "register", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String username = request.getParameter("username");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        Date dob = Date.valueOf(request.getParameter("dob"));
        String phone = request.getParameter("phone");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
       
        String username_err = "", password_err = "", firstname_err = "";
        String lastname_err;

        DAO dao = new DAO();

        boolean progressed = true;

        if (username.length() < 5 || username.length() > 32) {
            username_err = "Username must be between 5-32 characters.";
            request.setAttribute("USERNAME_ERR", username_err);
            progressed = false;
        }

        if (username.contains(" ")) {
            request.setAttribute("USERNAME_ERR", "Username cannot have whitespace!");
            progressed = false;
        }

        if (password.length() < 5 || password.length() > 40) {
            password_err = "Password must be between 5-40 characters.";
            request.setAttribute("PASSWORD_ERR", password_err);
            progressed = false;
        }

        if (firstname.isEmpty() || firstname.length() > 25) {
            firstname_err = "First name must be between 1-25 characters.";
            request.setAttribute("FIRSTNAME_ERR", firstname_err);
            progressed = false;
        }

        if (lastname.isEmpty() || lastname.length() > 25) {
            lastname_err = "Last name must be between 1-25 characters.";
            request.setAttribute("LASTNAME_ERR", lastname_err);
            progressed = false;
        }

        if (dao.getUser(username) != null) {
            request.setAttribute("USERNAME_EXISTED_ERR", "This username is unavailable.");
            progressed = false;
        }

        if (dao.isEmailExist(email) != null) {
            request.setAttribute("EMAIL_ERR", "This email has already been used.");
            progressed = false;
        }

        if (!RestrictUtils.isValidEmail(email)) {
            request.setAttribute("EMAIL_ERR", "Email is not valid.");
            progressed = false;
        }

        if (!RestrictUtils.isValidPhoneNumber(phone)) {
            request.setAttribute("PHONE_ERR", "Invalid phone number. Must be between 10-11 numbers.");
            progressed = false;
        }

        if (!RestrictUtils.isNotNull(address)) {
            request.setAttribute("ADDRESS_ERR", "Address is empty.");
            progressed = false;
        }

        if (!RestrictUtils.isPersonOlderThan13(dob)) {
            request.setAttribute("DOB_ERR", "You must be 13 years old or older to use the Service.");
            progressed = false;
        }

        request.setAttribute("USERNAME", username);
        request.setAttribute("FIRSTNAME", firstname);
        request.setAttribute("LASTNAME", lastname);
        request.setAttribute("EMAIL", email);
        request.setAttribute("PASSWORD", password);
        request.setAttribute("ADDRESS", address);
        request.setAttribute("DOB", dob);
        request.setAttribute("PHONE", phone);
        request.setAttribute("CITY", city);

        try {
            if (progressed) {
                dao.signup(username, firstname, lastname, email, password, address, dob, phone, city, state, "customer");
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }
}
