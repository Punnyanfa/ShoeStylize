package com.group7.tomcat.shoestylize.servlet.controller.api.post;

import com.group7.tomcat.shoestylize.servlet.entity.Account;
import com.group7.tomcat.shoestylize.servlet.entity.PendingBill;
import com.group7.tomcat.shoestylize.servlet.entity.Shoe;
import com.group7.tomcat.shoestylize.servlet.entity.ShoeExtra;
import com.group7.tomcat.shoestylize.servlet.sytem.OrderManager;
import com.group7.tomcat.shoestylize.servlet.sytem.ShoeExtraManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "api-stylize-form-input", urlPatterns = {"/api/stylize-form/input"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 3,
        maxRequestSize = 1024 * 1024 * 50)
public class APIPostStylizeFormInputServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        Account acc = (Account) session.getAttribute("user");
        if (acc == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        Shoe shoe = (Shoe) session.getAttribute("Shoe");
        if (shoe == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        try {

            //Form
            int userId = acc.getId();
            String firstName = request.getParameter("form-firstName"); //must
            String lastName = request.getParameter("form-lastName"); //must
            String email = request.getParameter("form-email"); //must
            String phone1 = request.getParameter("form-phone1"); //must
            String phone2 = request.getParameter("form-phone2");
            String address1 = request.getParameter("form-address1"); //must
            String address2 = request.getParameter("form-address2");
            String customerNote = request.getParameter("form-customerNote");
            String textureData = request.getParameter("form-textureData"); //must
            String sizeIdStr = request.getParameter("form-size"); //must
            Part texturePart = request.getPart("textureUpload");
            int sizeId = Integer.parseInt(sizeIdStr);

            //Reference mode
            String referenceInput = request.getParameter("form-referencePrompt");

            boolean progressed = true;
            if (firstName == null || firstName.trim().isEmpty()) {
                request.setAttribute("error_firstName", "First name should not be empty.");
                progressed = false;
            }

            if (lastName == null || lastName.trim().isEmpty()) {
                request.setAttribute("error_lastName", "Last name should not be empty.");
                progressed = false;
            }

            if (email == null || email.trim().isEmpty()) {
                request.setAttribute("error_email", "Email should not be empty.");
                progressed = false;
            }

            if (phone1 == null || phone1.trim().isEmpty()) {
                request.setAttribute("error_phone1", "Phone 1 should not be empty.");
                progressed = false;
            }

            if (address1 == null || address1.trim().isEmpty()) {
                request.setAttribute("error_address1", "Address 1 should not be empty.");
                progressed = false;
            }

            if (textureData == null || textureData.trim().isEmpty()) {
                request.setAttribute("error_textureData", "An error occurred with parsing texture.");
                progressed = false;
            }

            List<ShoeExtra> extras = new ArrayList<>();

            try {
                String[] accessories = request.getParameterValues("se");
                for (String accessoryId : accessories) {
                    extras.add(ShoeExtraManager.getShoeExtra(Integer.parseInt(accessoryId)));
                }
            } catch (Exception e) {
            }

            PendingBill bill = OrderManager.get().getBill(session);
            bill.addOrder(userId, shoe.getId(), firstName, lastName, email, phone1, phone2,
                    address1, address2, customerNote, textureData, texturePart.getInputStream(), sizeId, null, referenceInput, extras);

            response.sendRedirect(request.getContextPath() + "/carts");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/explore");
        }
    }

    private boolean isValidImage(Part filePart) {
        if (filePart == null) {
            return false;
        }
        String contentType = filePart.getContentType();
        return contentType != null && (contentType.equals("image/png") || contentType.equals("image/jpeg"));
    }

}
