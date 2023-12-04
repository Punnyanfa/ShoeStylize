package com.group7.tomcat.shoestylize.servlet.controller;

import com.group7.tomcat.shoestylize.servlet.entity.Account;
import com.group7.tomcat.shoestylize.servlet.entity.PendingBill;
import com.group7.tomcat.shoestylize.servlet.entity.PendingOrder;
import com.group7.tomcat.shoestylize.servlet.entity.ShoeExtra;
import com.group7.tomcat.shoestylize.servlet.sytem.EmailManager;
import com.group7.tomcat.shoestylize.servlet.sytem.OrderManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "cart-checkout", urlPatterns = {"/cart/checkout"})
public class CartCheckoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        PendingBill bill = OrderManager.get().getBill(request.getSession());

        if (bill.getOrders().isEmpty()) {
            request.getRequestDispatcher("/cart-checkout-empty.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/cart-checkout.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Account acc = (Account) session.getAttribute("user");
        if (acc == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        PendingBill bill = OrderManager.get().getBill(session);
        if (bill.getOrders().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/carts");
            return;
        }

        String canEmailStr = request.getParameter("form_email");
        boolean canEmail = canEmailStr != null && canEmailStr.equals("on");
        StringBuilder tableBuilder = new StringBuilder();

        for (PendingOrder order : bill.getOrders()) {
            StringBuilder subTableBuilder = new StringBuilder();
            subTableBuilder.append("<tr>\n" +
                    "                    <td><b>Base price</b></td>\n" +
                    "                    <td>" + order.getPrice() + "</td>\n" +
                    "                </tr>");
            for (ShoeExtra extras : order.getExtras()) {
                subTableBuilder.append("<tr>\n" +
                        "                    <td><b>Extra - " + extras.getTitle() + "</b></td>\n" +
                        "                    <td>" + order.getPrice() + "</td>\n" +
                        "                </tr>");
            }
            subTableBuilder.append("<tr>\n" +
                    "                    <td><b>Subtotal</b></td>\n" +
                    "                    <td>" + order.getTotalPrice() + "</td>\n" +
                    "                </tr>");

            String table = "<div class=\"table-node\">\n" +
                    "            <h3>" + order.getTitle() + " (Size: " + order.getSizeGender() + "/" + order.getSizeName() + ")</h3>\n" +
                    "            <table class=\"table table-striped table-hover\">\n" +
                    "                <thead>\n" +
                    "                <tr>\n" +
                    "                    <th>Item</th>\n" +
                    "                    <th>Value</th>\n" +
                    "                </tr>\n" +
                    "                </thead>\n" +
                    "                <tbody>\n" +
                    subTableBuilder.toString() +
                    "                </tbody>\n" +
                    "            </table>\n" +
                    "        </div>";
            tableBuilder.append(table);
        }

        if (canEmail) {
            EmailManager.sendMail(acc.getEmail(), response, "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\"\n" +
                    "          integrity=\"sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC\" crossorigin=\"anonymous\">\n" +
                    "    <title>ShoeStylize Mail</title>\n" +
                    "    <style>\n" +
                    "\t\tbody {\n" +
                    "\t\t\tdisplay: flex;\n" +
                    "\t\t\tflex-direction: column;\n" +
                    "\t\t\talign-items: center;\n" +
                    "\t\t}\n" +
                    "\n" +
                    "\t\t.mail-title {\n" +
                    "\t\t\tdisplay: flex;\n" +
                    "\t\t\tflex-direction: column;\n" +
                    "\t\t\talign-items: center;\n" +
                    "\t\t\twidth: 60vw;\n" +
                    "\t\t}\n" +
                    "\n" +
                    "        .table-container {\n" +
                    "            width: 100%;\n" +
                    "        }\n" +
                    "\n" +
                    "        .table-node > table {\n" +
                    "\t\t\twidth: 100%;\n" +
                    "        }\n" +
                    "\n" +
                    "\t\t#logo {\n" +
                    "\t\t\twidth: 150px;\n" +
                    "\t\t\theight: 150px;\n" +
                    "\t\t}\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<div class=\"mail-title\">\n" +
                    "    <img id=\"logo\" src=\"https://nueqvbuobysxbgpelaih.supabase.co/storage/v1/object/public/shoes-explore/home/logo.png\">\n" +
                    "    <h1>ShoeStylize Mail</h1>\n" +
                    "    <p>You have successfully purchased the following product(s).</p>\n" +
                    "    <div class=\"table-container\">\n" +
                    tableBuilder.toString() +
                    "    </div>\n" +
                    "\n" +
                    "    <h6>Shipping fee: $2.50</h6>" +
                    "    <h4>Grand total: $" + bill.getTotalPrice(true) + "</h4>\n" +
                    "    <div>\n" +
                    "        <p>Enjoy your experience with your newly customized shoe(s)!</p>\n" +
                    "    </div>\n" +
                    "</div>\n" +
                    "\n" +
                    "<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js\"\n" +
                    "        integrity=\"sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM\"\n" +
                    "        crossorigin=\"anonymous\"></script>\n" +
                    "</body>\n" +
                    "</html>\n");
        }

        bill.processBill();
        response.sendRedirect(request.getContextPath() + "/purchases");
    }
}
