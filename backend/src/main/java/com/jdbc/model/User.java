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

    public abstract User readAccount() throws SQLException;

    public abstract boolean updateAccount(User newInfoUser) throws SQLException;

    public abstract boolean deleteAccount() throws SQLException;

    //public abstract void sendPasswordReset(String email) throws SQLException;

    //public abstract void resetPassword() throws SQLException;
    
    public void resetPassword(String password) throws SQLException
    {
        String query = "update user set password = ? where userID = ?";
        //would also need to change the user objects password in as well
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, password);
        //"1" is for test only would userId in cases
        ps.setInt(2, id); 
        ps.executeUpdate();
    }

    public boolean verifyEmail(String mailer) throws SQLException
    {
        String query = "select email from User where userID = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        String dbEmail = "empty";
        while (rs.next())
            dbEmail = rs.getString("email");
        return mailer.compareTo(dbEmail) == 0;
    }

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
