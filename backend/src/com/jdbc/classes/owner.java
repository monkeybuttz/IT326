package com.jdbc.classes;

import java.util.List;
import com.jdbc.util.JDBCConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class owner extends user {

    static Connection con = JDBCConnection.getConnection();

    public owner(int id, String name, String username, String email, String password, long phoneNumber,
            String emailAuthenticateString, List<Message> conversation) {
        super(id, name, username, email, password, phoneNumber, email, conversation);
    }

    @Override
    public int createAccount(user theUser)throws SQLException{
        String query = "INSERT into owner(ownerID, name, username, password, email, phoneNUM) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, theUser.getOwnerID());
        ps.setString(2, theUser.getName());
        ps.setString(3, theUser.getUsername());
        ps.setString(4, theUser.getPassword());
        ps.setString(5, theUser.getEmail());
        ps.setLong(6, theUser.getPhoneNumber());
        ps.executeUpdate();
        return 0;
    }

    @Override
    public boolean updateAccount(user theUser) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteAccount(user theUser) throws SQLException {
        return false;
    }

    @Override
    public boolean sendMessage() {
        return false;
    }

    @Override
    public List<Message> loadMessages() {
        return null;
    }

    @Override
    public boolean authenticateEmail(String email) {
        return false;
    }

    @Override
    public void sendPasswordReset(String email) {

    }
    

    @Override
    public void resetPassword() {
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public int getOwnerID() {
        return this.id;
    }

    @Override
    public String getEmail() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public long getPhoneNumber() {
        return 0;
    }

    @Override
    public String getEmailAuth() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

}
