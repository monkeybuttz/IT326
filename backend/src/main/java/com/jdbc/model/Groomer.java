package com.jdbc.model;

import com.jdbc.util.JDBCConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Groomer extends User {

    static Connection con = JDBCConnection.getConnection();

    public Groomer(int id, String name, String username, String password, String email, long phoneNumber,
            boolean isGroomerr) {
        super(id, name, username, password, email, phoneNumber, isGroomerr);
    }

    @Override
    public int createAccount() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean updateAccount(User newUserInfo) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean deleteAccount() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void sendPasswordReset(String email) {
        // TODO Auto-generated method stub

    }

    @Override
    public void resetPassword() {
        // TODO Auto-generated method stub

    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getOwnerID() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getEmail() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getPhoneNumber() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean getIsGroomer() {
        // TODO Auto-generated method stub
        return false;
    }

}