package com.group7.tomcat.shoestylize.servlet.sytem;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;

public class EmailManager {

    public static void sendMail(String email, HttpServletResponse response, String content) {
        sendMail(email, response, content, null);
    }

    public static void sendMail(String email, HttpServletResponse response, String content, String title) {
        String host = "smtp.gmail.com";
        int port = 587;
        String username = "nguyenthienbao860@gmail.com";
        String password = "ghcgtnbamzyhhcgf";

        // Email content
        String recipient = email;
        String subject = title != null ? "ShoeStylize Mail - " + title : "ShoeStylize Mail";

        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getDefaultInstance(properties, null);

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setContent(content, "text/html");

            try (Transport transport = session.getTransport("smtp")) {
                transport.connect(host, port, username, password);
                transport.sendMessage(message, message.getAllRecipients());
            }
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (MessagingException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
