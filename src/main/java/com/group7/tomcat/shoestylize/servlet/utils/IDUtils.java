package com.group7.tomcat.shoestylize.servlet.utils;

import java.util.Random;
import java.util.UUID;

public class IDUtils {

    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }
    
    public static String generateRandomString(int length) {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }
}
