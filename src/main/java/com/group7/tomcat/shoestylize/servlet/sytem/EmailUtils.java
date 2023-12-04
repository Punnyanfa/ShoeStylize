package com.group7.tomcat.shoestylize.servlet.sytem;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailUtils {
    private static final String EMAIL_USERNAME = "shoestylize@gmail.com"; // Thay đổi thành địa chỉ email của bạn
    private static final String EMAIL_PASSWORD = "koke czav kmpj mqyi"; // Thay đổi thành mật khẩu email của bạn

    public static void sendHtmlEmail(String toEmail, String subject, String htmlBody) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com"); // Thay đổi nếu sử dụng dịch vụ email khác
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_USERNAME, EMAIL_PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL_USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);

            // Tạo một phần thân của email với định dạng HTML
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(htmlBody, "text/html; charset=UTF-8"); // Thêm charset=UTF-8 vào đây

            // Tạo multipart để kết hợp phần thân của email và các phần đính kèm (nếu có)
            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);

            Transport.send(message);

            System.out.println("Email sent successfully to: " + toEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
            System.err.println("Error sending email to: " + toEmail);
        }
    }
}
