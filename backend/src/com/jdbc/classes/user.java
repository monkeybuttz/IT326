package com.jdbc.classes; 
import java.util.List;

import util.JDBCConnection;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class user {
    
    public int id;
    public String name;
    public String username;
    public String email;
    public String password;
    public long phoneNumber;
    public String emailAuthenticate;
    public List <Message> conversation;

    static Connection con = JDBCConnection.getConnection();

    public user(int id, String name, String username, String email, String password, long phoneNumber,
            String emailAuthenticateString, List<Message> conversation) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.emailAuthenticate = emailAuthenticateString;
        this.conversation = conversation;
    }

    public abstract int createAccount(user theUser) throws SQLException;

    public abstract boolean updateAccount(user theUser) throws SQLException;

    public abstract boolean deleteAccount(user theUser) throws SQLException;

    public abstract boolean sendMessage() throws SQLException;

    public abstract List<Message> loadMessages() throws SQLException;

    public abstract boolean authenticateEmail(String email) throws SQLException;

    public abstract void sendPasswordReset(String email) throws SQLException;

    public abstract void resetPassword() throws SQLException;

    public abstract String toString();

    public abstract int getOwnerID();

    public abstract String getName();

    public abstract String getUsername();

    public abstract String getEmail();

    public abstract String getPassword();

    public abstract long getPhoneNumber();

    public abstract String getEmailAuth();

}
