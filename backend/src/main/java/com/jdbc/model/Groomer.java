package com.jdbc.model;

import java.util.List;

import com.jdbc.model.Message;
import com.jdbc.util.JDBCConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Groomer extends User {

    static Connection con = JDBCConnection.getConnection();

    public Groomer(int id, String username, String email, String password, long phoneNumber,
            String emailAuthenticateString, List<Message> conversation) {
        super(id, username, email, password, emailAuthenticateString, phoneNumber, emailAuthenticateString, conversation);
    }

    @Override
    public int createAccount(User theUser) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean updateAccount(User theUser) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean deleteAccount(User theUser) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean sendMessage() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<Message> loadMessages() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean authenticateEmail(String email) {
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
    public String getEmailAuth() {
        // TODO Auto-generated method stub
        return null;
    }

}