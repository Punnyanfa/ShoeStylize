package com.group7.tomcat.shoestylize.servlet.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EncodeUtils {

    public static String encodeURI(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8").replace("+", "%20");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(EncodeUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }

    public static String decodeURI(String s) {
        try {
            return URLDecoder.decode(s, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(EncodeUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }
}
