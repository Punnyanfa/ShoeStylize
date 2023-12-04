package com.group7.tomcat.shoestylize.servlet.controller.api.get;

import com.group7.tomcat.shoestylize.servlet.entity.Order;
import com.group7.tomcat.shoestylize.servlet.entity.PendingOrder;
import com.group7.tomcat.shoestylize.servlet.sytem.OrderManager;
import org.apache.commons.io.IOUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet(name = "api-pending-order-texture", urlPatterns = {"/api/pending-order-texture"})
public class ApiGetPendingOrderTexture extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("image/png");

        String idStr = request.getParameter("uuid");
        if (idStr == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        PendingOrder order = OrderManager.get().getPendingOrderByUUID(request.getSession(), idStr);
        if (order == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        byte[] imageBytes = order.getTextureImageBytes();
        if (imageBytes.length == 0) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        IOUtils.write(imageBytes, response.getOutputStream());
    }
}