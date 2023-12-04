package com.group7.tomcat.shoestylize.servlet.google;

public class Constants {
    public static String GOOGLE_CLIENT_ID = "989356944004-1q9hg3s8ifv3f6j26fra0qq4fc9ai8cd.apps.googleusercontent.com";
	public static String GOOGLE_CLIENT_SECRET = "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";
	public static String GOOGLE_REDIRECT_URI = "http://localhost:8080/ShoeStylize_Tomcat/LoginGoogleHandler";
	public static String GOOGLE_LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token";
	public static String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";
	public static String GOOGLE_GRANT_TYPE = "authorization_code";
}
