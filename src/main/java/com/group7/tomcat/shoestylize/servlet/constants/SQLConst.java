package com.group7.tomcat.shoestylize.servlet.constants;

public class SQLConst {

    //User
    public static final String GET_USER_BY_EMAIL = "SELECT * FROM \"User\" WHERE email=?";
    public static final String GET_USER_BY_USERNAME = "SELECT * FROM \"User\" WHERE username=?";
    public static final String GET_USER = "SELECT * FROM \"User\" WHERE username=? AND password=?";

    public static final String ADD_USER = "INSERT INTO \"User\" (username, firstname, lastname, email, password, address, date_birth, contact_number, city, state, token, role_id)\n" +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String ADD_USER_WITH_LOGIN_METHOD = "INSERT INTO \"User\" (username, firstname, lastname, email, password, address, date_birth, contact_number, city, state, role_id, token, login_method)\n" +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String UPDATE_USER = "UPDATE \"User\" SET "
            + "firstname=?, lastname=?, email=?, password=?, address=?, contact_number=?, city=?, state=? WHERE id=?";

}
