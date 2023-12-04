package com.group7.tomcat.shoestylize.servlet.controller;

import com.group7.tomcat.shoestylize.servlet.database.DBContext;
import com.group7.tomcat.shoestylize.servlet.database.DBObject;
import com.group7.tomcat.shoestylize.servlet.entity.News;
import com.group7.tomcat.shoestylize.servlet.sytem.NewsManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.group7.tomcat.shoestylize.servlet.sytem.NewsManager.getNews;

@WebServlet(name = "news", urlPatterns = {"/news"})
public class NewsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id");
        if (idStr == null) {
            request.setAttribute("LIST_NEWS", getNews());
            request.getRequestDispatcher("/news.jsp").forward(request, response);
        } else {
            try {
                int id = Integer.parseInt(idStr);
                News news = NewsManager.getNewsById(id);
                if (news == null) {
                    request.getRequestDispatcher("/error_404.jsp").forward(request, response);
                    return;
                }

                request.setAttribute("News", news);
                request.setAttribute("News_Recommend", NewsManager.getNewsRecommend());
                request.getRequestDispatcher("/news-info.jsp").forward(request, response);
            } catch (Exception e) {
                request.getRequestDispatcher("/error_404.jsp").forward(request, response);
            }
        }
    }
}
