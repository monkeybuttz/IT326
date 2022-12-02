package com.jdbc.model;

import com.jdbc.util.JDBCConnection;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class User {

    public int id;
    public String name;
    public String username;
    public String email;
    public String password;
    public long phoneNumber;
    public boolean isGroomerr;

    static Connection con = JDBCConnection.getConnection();

    public User(int id, String name, String username, String password, String email, Integer phoneNumber,
            boolean isGroomerr) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.isGroomerr = isGroomerr;
    }

    public abstract int createAccount(User theUser) throws SQLException;

    public abstract boolean updateAccount(User theUser) throws SQLException;

    public abstract boolean deleteAccount(User theUser) throws SQLException;

    public abstract void sendPasswordReset(String email) throws SQLException;

    public abstract void resetPassword() throws SQLException;

    public abstract String toString();

    public abstract int getOwnerID();

    public abstract String getName();

    public abstract String getUsername();

    public abstract String getEmail();

    public abstract String getPassword();

    public abstract int getPhoneNumber();

    public abstract boolean getIsGroomer();

}
