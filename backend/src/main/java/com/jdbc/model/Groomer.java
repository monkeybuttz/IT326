package com.jdbc.model;

import com.jdbc.util.JDBCConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Groomer extends User {

    static Connection con = JDBCConnection.getConnection();

    @Override
    public int createAccount() throws SQLException{
        String query = "INSERT into user(userID, name, username, password, email, phoneNUM, isGroomer) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, this.getOwnerID());
        ps.setString(2, this.getName());
        ps.setString(3, this.getUsername());
        ps.setString(4, this.getPassword());
        ps.setString(5, this.getEmail());
        ps.setLong(6, this.getPhoneNumber());
        ps.setBoolean(7, this.getIsGroomer());
        ps.executeUpdate();
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
    public String getUsername() {
        return this.username;
    }

    @Override
    public int getOwnerID() {
        return this.id;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public long getPhoneNumber() {
        return this.phoneNumber;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean getIsGroomer() {
        return false;
    }

    @Override
    public void setOwnerID(int id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setName(String name) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setUsername(String username) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setEmail(String email) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setPassword(String password) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setPhoneNumber(long pn) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setIsGroomer(boolean option) {
        // TODO Auto-generated method stub
        
    }

}