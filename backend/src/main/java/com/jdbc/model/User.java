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
    public boolean isGroomer;

    static Connection con = JDBCConnection.getConnection();

    public abstract int createAccount() throws SQLException;

    public abstract boolean readAccount() throws SQLException;

    public abstract boolean updateAccount(User newInfoUser) throws SQLException;

    public abstract boolean deleteAccount() throws SQLException;

    public abstract void sendPasswordReset(String email) throws SQLException;

    public abstract void resetPassword() throws SQLException;

    public abstract int getID();

    public abstract String getName();

    public abstract String getUsername();

    public abstract String getEmail();

    public abstract String getPassword();

    public abstract long getPhoneNumber();

    public abstract boolean getIsGroomer();

    public abstract void setID(int id);

    public abstract void setName(String name);

    public abstract void setUsername(String username);

    public abstract void setEmail(String email);

    public abstract void setPassword(String password);

    public abstract void setPhoneNumber(long pn);

    public abstract void setIsGroomer(boolean option);

}
