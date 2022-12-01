package com.jdbc.classes;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class user {

    public int id;
    public String username;
    private String email;
    private String password;
    public int phoneNumber;
    public String emailAuthenticate;
    public List<Message> conversation;

    public abstract user{
        this.id=id;
    }

    public abstract int createAccount() throws SQLException;

    public abstract boolean updateAccount() throws SQLException;

    public abstract boolean deleteAccount() throws SQLException;

    public abstract boolean sendMessage() throws SQLException;

    public abstract List<Message> loadMessages() throws SQLException;

    public abstract boolean authenticateEmail(String email) throws SQLException;

    public abstract void sendPasswordReset(String email) throws SQLException;

    public abstract void resetPassword() throws SQLException;

    public abstract String toString();

}
