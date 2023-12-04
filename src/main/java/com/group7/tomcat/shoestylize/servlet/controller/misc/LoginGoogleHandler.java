package com.group7.tomcat.shoestylize.servlet.controller.misc;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.group7.tomcat.shoestylize.servlet.dao.DAO;
import com.group7.tomcat.shoestylize.servlet.entity.Account;
import com.group7.tomcat.shoestylize.servlet.google.Constants;
import com.group7.tomcat.shoestylize.servlet.google.UserGoogleDto;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

@WebServlet(urlPatterns = {"/LoginGoogleHandler"})
public class LoginGoogleHandler extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String code = request.getParameter("code");
        String accessToken = getToken(code);
        UserGoogleDto user = getUserInfo(accessToken);

        Account acc = new DAO().isWithGoogle(user);
        if (acc != null) {
            if (acc.getLoginMethod().equals("normal")) {
                request.setAttribute("error", "This account has already registered through normal account!");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                return;
            }
            session.setAttribute("user", acc);
            response.sendRedirect(request.getContextPath());
        } else {
            session.setAttribute("GOOGLE_USER", user);
            session.setAttribute("FIRSTNAME", user.getFamily_name());
            session.setAttribute("LASTNAME", user.getGiven_name());
            session.setAttribute("EMAIL", user.getEmail());
            request.getRequestDispatcher("/register_google.jsp").forward(request, response);
        }

    }

    public static String getToken(String code) throws ClientProtocolException, IOException {
        String response = Request.Post(Constants.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(Form.form().add("client_id", Constants.GOOGLE_CLIENT_ID)
                        .add("client_secret", Constants.GOOGLE_CLIENT_SECRET)
                        .add("redirect_uri", Constants.GOOGLE_REDIRECT_URI).add("code", code)
                        .add("grant_type", Constants.GOOGLE_GRANT_TYPE).build())
                .execute().returnContent().asString();

        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }

    public static UserGoogleDto getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = Constants.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();
        UserGoogleDto googlePojo = new Gson().fromJson(response, UserGoogleDto.class);

        return googlePojo;
    }

}
