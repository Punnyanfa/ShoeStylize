package com.group7.tomcat.shoestylize.servlet.controller.api.post;

import com.group7.tomcat.shoestylize.servlet.dao.DAO;
import com.group7.tomcat.shoestylize.servlet.entity.Account;
import com.group7.tomcat.shoestylize.servlet.utils.RestrictUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "api-user-settings", urlPatterns = {"/api/user-settings"})
public class ApiUserSettings extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        try {
            Account acc = (Account) session.getAttribute("user");
            if (acc == null) {
                request.getRequestDispatcher("/error_403.jsp").forward(request, response);
                return;
            }

            String firstname = request.getParameter("form-firstname");
            String lastname = request.getParameter("form-lastname");
            String email = request.getParameter("form-email");
            String address = request.getParameter("form-address");
            Date dob = Date.valueOf(request.getParameter("form-dob"));
            String phone = request.getParameter("form-phone");
            String city = request.getParameter("form-city");
            String state = request.getParameter("form-state");

            String password = request.getParameter("form-password");
            String repassword = request.getParameter("form-repassword");

            boolean progressed = true;

            if (firstname.trim().isEmpty() || firstname.length() > 25) {
                request.setAttribute("FIRSTNAME_ERR", "First name must be between 1-25 characters.");
                progressed = false;
            }

            if (lastname.trim().isEmpty() || lastname.length() > 25) {
                request.setAttribute("LASTNAME_ERR", "Last name must be between 1-25 characters.");
                progressed = false;
            }

            DAO dao = new DAO();

            if (!acc.getEmail().equals(email)) {
                if (dao.isEmailExist(email) != null) {
                    request.setAttribute("EMAIL_ERR", "This email has already been used.");
                    progressed = false;
                } else if (!RestrictUtils.isValidEmail(email)) {
                    request.setAttribute("EMAIL_ERR", "Email is not valid.");
                    progressed = false;
                }
            }

            if (!RestrictUtils.isValidPhoneNumber(phone)) {
                request.setAttribute("PHONE_ERR", "Invalid phone number. Must be between 10-11 numbers.");
                progressed = false;
            }

            if (!RestrictUtils.isNotNull(address)) {
                request.setAttribute("ADDRESS_ERR", "Address is empty.");
                progressed = false;
            }

            if (dob == null) {
                request.setAttribute("DOB_ERR", "Date is required.");
                progressed = false;
            } else {
                if (!RestrictUtils.isPersonOlderThan13(dob)) {
                    request.setAttribute("DOB_ERR", "You must be 13 years old or older to use the Service.");
                    progressed = false;
                }
            }

            if (!password.trim().isEmpty()) {
                if (password.length() < 5 || password.length() > 40) {
                    request.setAttribute("PASSWORD_ERR", "Password must be between 5-40 characters.");
                    progressed = false;
                }
                if (!password.equals(repassword)) {
                    request.setAttribute("REPASSWORD_ERR", "Confirmed password does not match.");
                    progressed = false;
                }
            }

            if (progressed) {
                dao.updateAccount(firstname, lastname, email, password, address, phone, city, state, acc.getId());
                request.setAttribute("MSG_SUCCESS", "Account has been updated.");
            }

            session.setAttribute("user", dao.getUser(acc.getUsername()));
            session.setAttribute("SETTINGS_SUCCESS_MSG", "Account has been updated.");
            response.sendRedirect("/settings");
        } catch (Exception e) {
            request.getRequestDispatcher("/error_403.jsp").forward(request, response);
        }
    }

}
