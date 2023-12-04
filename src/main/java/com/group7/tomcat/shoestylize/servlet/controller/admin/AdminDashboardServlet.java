package com.group7.tomcat.shoestylize.servlet.controller.admin;

import com.group7.tomcat.shoestylize.servlet.database.DBContext;
import com.group7.tomcat.shoestylize.servlet.database.DBObject;
import com.group7.tomcat.shoestylize.servlet.entity.Account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "admin-dashboard", urlPatterns = {"/admin/dashboard"})
public class AdminDashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        Account acc = (Account) session.getAttribute("user");
        if (!acc.getRole().equals("admin")) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        List<DBObject> result_users = DBContext.executeQuery("SELECT COUNT(id) AS user_count FROM \"User\"");
        int numOfUsers = result_users.get(0).getInt("user_count");
        request.setAttribute("NUMBER_OF_USERS", numOfUsers);

        List<DBObject> result_admin = DBContext.executeQuery("SELECT COUNT(id) AS user_count_admin FROM \"User\" WHERE role_id = 'admin'");
        int numOfAdmins = result_admin.get(0).getInt("user_count_admin");
        request.setAttribute("NUMBER_OF_ADMIN", numOfAdmins);

        List<DBObject> result_service_provider
                = DBContext.executeQuery("SELECT COUNT(id) AS user_count_service_provider FROM \"User\" WHERE role_id = 'service_provider'");
        int numOfServiceProviders = result_service_provider.get(0).getInt("user_count_service_provider");
        request.setAttribute("NUMBER_OF_SERVICE_PROVIDERS", numOfServiceProviders);

        List<DBObject> result_customers
                = DBContext.executeQuery("SELECT COUNT(id) AS user_count_customer FROM \"User\" WHERE role_id = 'customer'");
        int numOfCustomers = result_customers.get(0).getInt("user_count_customer");
        request.setAttribute("NUMBER_OF_CUSTOMERS", numOfCustomers);

        List<DBObject> result_orders
                = DBContext.executeQuery("SELECT COUNT(id) AS number_of_orders FROM \"Order_Shoe\"");
        int numOfOrders = result_orders.get(0).getInt("number_of_orders");
        request.setAttribute("NUMBER_OF_ORDERS", numOfOrders);

        List<DBObject> result_styles
                = DBContext.executeQuery("SELECT COUNT(id) AS number_of_styles FROM \"Shoe\"");
        int numOfStyles = result_styles.get(0).getInt("number_of_styles");
        request.setAttribute("NUMBER_OF_STYLES", numOfStyles);

        List<DBObject> result_one_day
                = DBContext.executeQuery("SELECT COALESCE(SUM(total_price), 0) AS sum_price " +
                "FROM \"Order_Shoe\" " +
                "WHERE date_created >= CURRENT_DATE " +
                "AND date_created < CURRENT_DATE + 1");
        int totalOneDay = result_one_day.get(0).getInt("sum_price");
        request.setAttribute("NUMBER_OF_PROFITS_IN_1_DAY", totalOneDay);

        List<DBObject> result_thirty_days
                = DBContext.executeQuery("SELECT COALESCE(SUM(total_price), 0) AS sum_price " +
                "FROM \"Order_Shoe\" " +
                "WHERE date_created >= CURRENT_DATE - 29 " +
                "AND date_created < CURRENT_DATE + 1");
        int totalThirtyDays = result_thirty_days.get(0).getInt("sum_price");
        request.setAttribute("NUMBER_OF_PROFITS_IN_30_DAYS", totalThirtyDays);

        List<DBObject> result_ninety_days
                = DBContext.executeQuery("SELECT COALESCE(SUM(total_price), 0) AS sum_price " +
                "FROM \"Order_Shoe\" " +
                "WHERE date_created >= CURRENT_DATE - 89 " +
                "AND date_created < CURRENT_DATE + 1");
        int totalNinetyDays = result_ninety_days.get(0).getInt("sum_price");
        request.setAttribute("NUMBER_OF_PROFITS_IN_90_DAYS", totalNinetyDays);

        List<DBObject> result_pending = DBContext.executeQuery("SELECT COUNT(id) AS count_order FROM \"Order_Shoe\" WHERE order_shoe_status = 'pending'");
        int numOfOrdersPending = result_pending.get(0).getInt("count_order");
        request.setAttribute("NUMBER_OF_PENDING", numOfOrdersPending);

        List<DBObject> result_progress = DBContext.executeQuery("SELECT COUNT(id) AS count_order FROM \"Order_Shoe\" WHERE order_shoe_status = 'in_progress'");
        int numOfOrdersProgress = result_progress.get(0).getInt("count_order");
        request.setAttribute("NUMBER_OF_PROGRESS", numOfOrdersProgress);

        List<DBObject> result_cancelled = DBContext.executeQuery("SELECT COUNT(id) AS count_order FROM \"Order_Shoe\" WHERE order_shoe_status = 'cancelled'");
        int numOfOrdersCancelled = result_cancelled.get(0).getInt("count_order");
        request.setAttribute("NUMBER_OF_CANCELLED", numOfOrdersCancelled);

        List<DBObject> result_success = DBContext.executeQuery("SELECT COUNT(id) AS count_order FROM \"Order_Shoe\" WHERE order_shoe_status = 'success'");
        int numOfOrdersSuccess = result_success.get(0).getInt("count_order");
        request.setAttribute("NUMBER_OF_SUCCESS", numOfOrdersSuccess);

        request.getRequestDispatcher("/admin_dashboard.jsp").forward(request, response);
    }
}
