package com.jdbc.model;

import com.jdbc.util.JDBCConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Owner extends User {

    static Connection con = JDBCConnection.getConnection();

    @Override
    public int createAccount() throws SQLException {
        String query = "INSERT into user(name, username, password, email, phoneNUM, isGroomer) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, this.getID());
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
    public Owner readAccount() throws SQLException {
        String query = "select * from user where userID =?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, this.getID());
        Owner own = new Owner();
        ResultSet rs = ps.executeQuery();
        boolean c = false;
        while (rs.next()) {
            c = true;
            own.setName(rs.getString("name"));
            own.setUsername(rs.getString("username"));
            own.setPassword(rs.getString("password"));
            own.setEmail(rs.getString("email"));
            own.setPhoneNumber(rs.getInt("phoneNUM"));
            own.setIsGroomer(rs.getBoolean("isGroomer"));
        }
        if (c) {
            return own;
        } 
        else {
            return null;
        }
    }

    @Override
    public boolean updateAccount(User newUserInfo) throws SQLException {
        String query = "update user set name = ?, username = ?, password = ?, email = ?, phoneNUM = ?, isGroomer = ? where userId = ? ";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, newUserInfo.getName());
        ps.setString(2, newUserInfo.getUsername());
        ps.setString(3, newUserInfo.getPassword());
        ps.setString(4, newUserInfo.getEmail());
        ps.setLong(5, newUserInfo.getPhoneNumber());
        ps.setBoolean(6, newUserInfo.getIsGroomer());
        ps.setInt(7, this.getID());
        ps.executeUpdate();
        return true;
    }

    @Override
    public boolean deleteAccount() throws SQLException {
        String query = "delete from user where userID =?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, this.getID());
        ps.executeUpdate();
        return true;
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
    public String getUsername() {
        return this.username;
    }

    @Override
    public int getID() {
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
    public void setID(int id) {
        this.id = id;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setPhoneNumber(long pn) {
        this.phoneNumber = pn;
    }

    @Override
    public void setIsGroomer(boolean option) {
        this.isGroomer = option;
    }

}
