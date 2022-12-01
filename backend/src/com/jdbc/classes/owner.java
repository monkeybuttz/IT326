package com.jdbc.classes;

import java.util.List;

public class owner extends user {

    @Override
    public int createAccount() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean updateAccount() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean deleteAccount() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean sendMessage() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<Message> loadMessages() {
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

    @Override
    public void resetPassword() {
        // TODO Auto-generated method stub
        
    }
    
}
