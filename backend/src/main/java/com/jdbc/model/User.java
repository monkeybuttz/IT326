package com.jdbc.model;

import com.jdbc.util.JDBCConnection;
import java.sql.*;

public abstract class User {

    public int id;
    public String name;
    public String username;
    public String email;
    public String password;
    public long phoneNumber;
    public int isGroomer;

    static Connection con = JDBCConnection.getConnection();

    public abstract int createAccount() throws SQLException;

    public abstract User readAccount() throws SQLException;

    public abstract boolean updateAccount(User newInfoUser) throws SQLException;

    public abstract boolean deleteAccount() throws SQLException;

    public void resetPassword(String password) throws SQLException {
        String query = "update user set password = ? where userID = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, password);
        this.setPassword(password);
        ps.setInt(2, this.id);
        ps.executeUpdate();
    }

    public boolean verifyCreditials(String mailer, String username, int phoneNum) throws SQLException {
        String query = "select * from User where userID = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        String dbEmail = "empty";
        String dbUser = "empty";
        int dbNum = 0;
        while (rs.next()) {
            dbEmail = rs.getString("email");
            dbUser = rs.getString("username");
            dbNum = rs.getInt("phoneNum");
        }
        return ((mailer.compareTo(dbEmail) == 0) && (dbUser.compareTo(username) == 0) && (dbNum == phoneNum));
    }

    public int getIdFromDB() throws SQLException {
        String query = "Select userID from user where username = ? and email = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, this.username);
        ps.setString(2, this.email);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("userID");
            this.setID(id);
            return id;
        } else
            return 0;
    }

    public abstract int getID();

    public abstract String getName();

    public abstract String getUsername();

    public abstract String getEmail();

    public abstract String getPassword();

    public abstract long getPhoneNumber();

    public abstract int getIsGroomer();

    public abstract void setID(int id);

    public abstract void setName(String name);

    public abstract void setUsername(String username);

    public abstract void setEmail(String email);

    public abstract void setPassword(String password);

    public abstract void setPhoneNumber(long pn);

    public abstract void setIsGroomer(int option);

}
