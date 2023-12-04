package com.group7.tomcat.shoestylize.servlet.utils;

public class GeneralUtils {

    public static boolean checkTextExists(String s) {
        return s != null && s.trim().isEmpty();
    }
}
