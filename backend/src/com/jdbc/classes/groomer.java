package com.jdbc.classes;

<<<<<<< Updated upstream
public class groomer extends user{
=======
import java.util.List;
import com.jdbc.util.JDBCConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class groomer extends user {

    static Connection con = JDBCConnection.getConnection();

    public groomer(int id, String username, String email, String password, long phoneNumber,
            String emailAuthenticateString, List<Message> conversation) {
        super(id, username, email, password, emailAuthenticateString, phoneNumber, emailAuthenticateString, conversation);
    }
>>>>>>> Stashed changes

    @Override
    public int createAccount(user theUser) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean updateAccount(user theUser) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean deleteAccount(user theUser) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean sendMessage() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Message loadMessages() {
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
<<<<<<< Updated upstream
    
=======

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

>>>>>>> Stashed changes
}