package com.group7.tomcat.shoestylize.servlet.controller;

import com.group7.tomcat.shoestylize.servlet.database.DBContext;
import com.group7.tomcat.shoestylize.servlet.database.DBObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "explore", urlPatterns = {"/explore"})
public class ExploreServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String sort = request.getParameter("sortAlphabet");
        String priceSort = request.getParameter("sortPrice");
        String query = request.getParameter("query");
        String[] selectedShoeTypes = request.getParameterValues("shoeType[]");

        List<DBObject> data;

        String sql = "SELECT * FROM \"Shoe\"";

        if (selectedShoeTypes != null && selectedShoeTypes.length > 0) {
            sql += " WHERE shoe_type IN (";
            for (int i = 0; i < selectedShoeTypes.length; i++) {
                sql += "'" + selectedShoeTypes[i] + "'";
                if (i < selectedShoeTypes.length - 1) {
                    sql += ", ";
                }
            }
            sql += ")";
        }

        if (sort != null) {
            if (sort.equalsIgnoreCase("a-z")) {
                sql += " ORDER BY title ASC";
            } else if (sort.equalsIgnoreCase("z-a")) {
                sql += " ORDER BY title DESC";
            }
        }

        if (priceSort != null) {
            if (priceSort.equalsIgnoreCase("asc")) {
                if (sort == null) {
                    sql += " ORDER BY price ASC";
                } else {
                    sql += ", price ASC";
                }
            } else if (priceSort.equalsIgnoreCase("desc")) {
                if (sort == null) {
                    sql += " ORDER BY price DESC";
                } else {
                    sql += ", price DESC";
                }
            }
        }

        data = queryData(query, sql);

        request.setAttribute("shoes", data);
        List<String> listSelectedType = new ArrayList<>();
        if (selectedShoeTypes != null) {
            listSelectedType = Arrays.asList(selectedShoeTypes);
        }

        request.setAttribute("filterType", listSelectedType);
        request.getRequestDispatcher("/explore.jsp").forward(request, response);
    }

    private List<DBObject> queryData(String query, String sql) {
        if (query != null) {
            return DBContext.executeQuery(sql + " WHERE title ILIKE ?", "%" + query + "%");
        } else {
            return DBContext.executeQuery(sql);
        }
    }
}
