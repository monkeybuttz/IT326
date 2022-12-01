package com.jdbc.classes; 
import java.util.List;

public abstract class user {
    
    public int id;
    public String username;
    private String email; 
    private String password;
    public int phoneNumber;
    public String emailAuthenticate;
    public List <Message> conversation;

    public abstract int createAccount();
    public abstract boolean updateAccount();
    public abstract boolean deleteAccount();
    public abstract boolean sendMessage();
    public abstract Message loadMessages();
    public abstract boolean authenticateEmail(String email);
    public abstract void sendPasswordReset(String email);

}
