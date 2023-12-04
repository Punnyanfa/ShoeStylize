package com.group7.tomcat.shoestylize.servlet.dao;

import com.group7.tomcat.shoestylize.servlet.constants.SQLConst;
import com.group7.tomcat.shoestylize.servlet.database.DBContext;
import com.group7.tomcat.shoestylize.servlet.database.DBObject;
import com.group7.tomcat.shoestylize.servlet.entity.Account;
import com.group7.tomcat.shoestylize.servlet.google.UserGoogleDto;
import com.group7.tomcat.shoestylize.servlet.utils.IDUtils;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;

public class DAO {

    public boolean signup(String username, String firstname, String lastname, String email, String password,
                           String address, Date dob, String phone, String city, String state, String role) {
        try {
            DBContext.executeUpdate(SQLConst.ADD_USER, username, firstname, lastname, email, password, address, dob, phone, city, state, IDUtils.generateRandomString(50), role);
        } catch (Exception ignored) {
        }
        return true;
    }

    public boolean signup(String username, String firstname, String lastname, String email, String password,
                           String address, Date dob, String phone, String city, String state, String role, String loginMethod) {
        try {
            DBContext.executeUpdate(SQLConst.ADD_USER_WITH_LOGIN_METHOD, username, firstname, lastname, email, password, address, dob, phone, city, state, role, IDUtils.generateRandomString(50), loginMethod);
        } catch (Exception ignored) {
            return false;
        }
        return true;
    }

    public Account getUser(String username) {
        try {
            List<DBObject> dbs = DBContext.executeQuery(SQLConst.GET_USER_BY_USERNAME, username);
            if (!dbs.isEmpty()) {
                DBObject db = dbs.get(0);
                return new Account(
                        db.getInt("id"),
                        db.getString("username"),
                        db.getString("firstname"),
                        db.getString("lastname"),
                        db.getString("email"),
                        db.getString("password"),
                        db.getString("address"),
                        db.getDate("date_birth"),
                        db.getString("contact_number"),
                        db.getString("city"),
                        db.getString("state"),
                        db.getString("role_id"),
                        db.getString("login_method"),
                        db.getString("token"));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public Account getUser(String username, String password) {
        try {
            List<DBObject> dbs = DBContext.executeQuery(SQLConst.GET_USER, username, password);
            if (!dbs.isEmpty()) {
                DBObject db = dbs.get(0);
                return new Account(
                        db.getInt("id"),
                        db.getString("username"),
                        db.getString("firstname"),
                        db.getString("lastname"),
                        db.getString("email"),
                        db.getString("password"),
                        db.getString("address"),
                        db.getDate("date_birth"),
                        db.getString("contact_number"),
                        db.getString("city"),
                        db.getString("state"),
                        db.getString("role_id"),
                        db.getString("login_method"),
                        db.getString("token"));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static Account getUserById(int id) {
        try {
            List<DBObject> dbs = DBContext.executeQuery("SELECT * FROM \"User\" WHERE id=?", id);
            if (!dbs.isEmpty()) {
                DBObject db = dbs.get(0);
                return new Account(
                        db.getInt("id"),
                        db.getString("username"),
                        db.getString("firstname"),
                        db.getString("lastname"),
                        db.getString("email"),
                        db.getString("password"),
                        db.getString("address"),
                        db.getDate("date_birth"),
                        db.getString("contact_number"),
                        db.getString("city"),
                        db.getString("state"),
                        db.getString("role_id"),
                        db.getString("login_method"),
                        db.getString("token"));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public Account isEmailExist(String email) {
        try {
            List<DBObject> dbs = DBContext.executeQuery(SQLConst.GET_USER_BY_EMAIL, email);
            if (!dbs.isEmpty()) {
                DBObject db = dbs.get(0);
                return new Account(
                        db.getInt("id"),
                        db.getString("username"),
                        db.getString("firstname"),
                        db.getString("lastname"),
                        db.getString("email"),
                        db.getString("password"),
                        db.getString("address"),
                        db.getDate("date_birth"),
                        db.getString("contact_number"),
                        db.getString("city"),
                        db.getString("state"),
                        db.getString("role_id"),
                        db.getString("login_method"),
                        db.getString("token"));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public boolean updateAccount(String firstname, String lastname, String email, String password,
                                  String address, String phone, String city, String state, int userID) {

        DBContext.executeUpdate(SQLConst.UPDATE_USER, firstname, lastname, email, password, address, phone, city, state, userID);
        return true;
    }

    public List<Account> getAllUsers() {
        List<DBObject> dbs = DBContext.executeQuery("SELECT * FROM \"User\"");
        List<Account> accounts = new ArrayList<>();

        for (DBObject db : dbs) {
            Account acc = new Account(
                    db.getInt("id"),
                    db.getString("username"),
                    db.getString("firstname"),
                    db.getString("lastname"),
                    db.getString("email"),
                    db.getString("password"),
                    db.getString("address"),
                    db.getDate("date_birth"),
                    db.getString("contact_number"),
                    db.getString("city"),
                    db.getString("state"),
                    db.getString("role_id"),
                    db.getString("login_method"),
                    db.getString("token"));
            accounts.add(acc);
        }

        return accounts;
    }

    public Account isWithGoogle(UserGoogleDto dto) {
        try {
            Account acc = isEmailExist(dto.getEmail());
            if (acc != null) {
                return acc;
            }
        } catch (Exception ignored) {
            return null;
        }
        return null;
    }

    public Account matchingToken(String token) {
        try {
            List<DBObject> dbs = DBContext.executeQuery("SELECT * FROM \"User\" WHERE token=?", token);
            if (!dbs.isEmpty()) {
                DBObject db = dbs.get(0);
                return new Account(
                        db.getInt("id"),
                        db.getString("username"),
                        db.getString("firstname"),
                        db.getString("lastname"),
                        db.getString("email"),
                        db.getString("password"),
                        db.getString("address"),
                        db.getDate("date_birth"),
                        db.getString("contact_number"),
                        db.getString("city"),
                        db.getString("state"),
                        db.getString("role_id"),
                        db.getString("login_method"),
                        db.getString("token"));
            }
        } catch (Exception e) {
        }
        return null;
    }
}
