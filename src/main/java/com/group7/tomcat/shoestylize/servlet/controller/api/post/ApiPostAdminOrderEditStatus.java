package com.group7.tomcat.shoestylize.servlet.controller.api.post;

import com.group7.tomcat.shoestylize.servlet.database.DBContext;
import com.group7.tomcat.shoestylize.servlet.entity.Order;
import com.group7.tomcat.shoestylize.servlet.sytem.EmailUtils;
import com.group7.tomcat.shoestylize.servlet.sytem.OrderManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "api-post-admin-order-edit-status", urlPatterns = {"/api/admin/order-edit/status"})
public class ApiPostAdminOrderEditStatus extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        String idStr = req.getParameter("id");
        String note = req.getParameter("order-sp-note");
        if (note == null) {
            note = "";
        }
        int id = Integer.parseInt(idStr);

        Order order = OrderManager.get().getOrderById(id);

        String emailContent = "Đơn hàng của bạn đang được xử lý.";
        // noi dung email duoi dang html bang
        emailContent = "<html>"
                + "<head>"
                + "<meta charset=\"UTF-8\">"
                + // Thêm dòng này
                "<style>"
                + "table {"
                + "    font-family: Arial, sans-serif;"
                + "    border-collapse: collapse;"
                + "    width: 100%;"
                + "}"
                + "th, td {"
                + "    border: 1px solid #dddddd;"
                + "    text-align: left;"
                + "    padding: 8px;"
                + "}"
                + "tr:nth-child(even) {"
                + "    background-color: #f2f2f2;"
                + "}"
                + "</style>"
                + "</head>"
                + "<body>"
                + "<p>Thông tin đơn hàng:</p>"
                + "<table>"
                + "<tr>"
                + "<th>Tên sản phẩm</th>"
                + "<th>Size</th>"
                + "<th>Giá</th>"
                + "<th>Tên khách hàng</th>"
                + "</tr>"
                + "<tr>"
                + "<td>" + order.getShoe().getTitle() + "</td>"
                + "<td>" + order.getSize().getGender() + "/" + order.getSize().getSizeName() + "</td>"
                + "<td>" + order.getShoe().getPrice() + "</td>"
                + "<td>" + order.getUser().getFullname() + "</td>"
                + "</tr>"
                + "</table>"
                + "<p>Cảm ơn bạn đã đặt hàng!</p>"
                + "</body>"
                + "</html>";

        String email = order.getUser().getEmail();
        if (email != null) {
            if (order.getOrderShoeStatus().equals("in_progress")) {
                EmailUtils.sendHtmlEmail(email, "ShoeStylize - Order in progress", emailContent);
            }
        }

        DBContext.executeUpdate("UPDATE \"Order_Shoe\" SET order_shoe_status=?, service_provider_note=? WHERE id=?",
                "in_progress", note, id);

        resp.sendRedirect(req.getContextPath() + "/admin/orders");
    }
}