package com.group7.tomcat.shoestylize.servlet.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RestrictUtils {

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    public static boolean isValidPhoneNumber(String s) {
        if (s == null || s.trim().isEmpty()) return false;
        String digitsOnly = s.replaceAll("[^0-9]", "");
        int length = digitsOnly.length();
        return length >= 10 && length <= 11;
    }

    public static boolean isNotNull(String s) {
        return s != null && !s.trim().isEmpty();
    }

    public static boolean isPersonOlderThan13(Date dateOfBirth) {
        if (dateOfBirth == null) return false;
        Date currentDate = new Date();
        long ageInMillis = currentDate.getTime() - dateOfBirth.getTime();
        long ageInYears = ageInMillis / (365L * 24 * 60 * 60 * 1000);
        return ageInYears >= 13;
    }

    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
