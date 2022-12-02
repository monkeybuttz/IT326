package com.jdbc.model;

import com.jdbc.util.JDBCConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Owner extends User {

    static Connection con = JDBCConnection.getConnection();

    public Owner(int id, String name, String username, String password, String email, int phuneNumber,
            String string, boolean isGroomer) {
        super(id, name, username, password, email, phuneNumber, isGroomer);
    }

    @Override
    public int createAccount(User theUser) throws SQLException {
        String query = "INSERT into owner(ownerID, name, username, password, email, phoneNUM, isGroomer) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, theUser.getOwnerID());
        ps.setString(2, theUser.getName());
        ps.setString(3, theUser.getUsername());
        ps.setString(4, theUser.getPassword());
        ps.setString(5, theUser.getEmail());
        ps.setLong(6, theUser.getPhoneNumber());
        ps.setBoolean(7, theUser.getIsGroomer());
        ps.executeUpdate();
        return 0;
    }

    @Override
    public boolean updateAccount(User theUser) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteAccount(User theUser) throws SQLException {
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
    public String getName() {
        return null;
    }

    @Override
    public boolean getIsGroomer() {
        return false;
    }

}
