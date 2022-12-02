package com.jdbc.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

import com.jdbc.util.JDBCConnection;

import com.jdbc.model.Message;

public class MessageImpl
{
    static Connection con = JDBCConnection.getConnection();

    public void add(Message message) throws SQLException
    {
        String text = message.getText();
        int senderID = message.getSenderId();
        int receiverID = message.getReceiverId();

        String query = "INSERT into message(post, senderID, receiverID) VALUES (?, ?, ?)";

        
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, text);
        ps.setInt(2, senderID);
        ps.setInt(3, receiverID);
        ps.executeUpdate();
    }

    public void delete(int id) throws SQLException
    {
        String query = "delete from message where messageID =?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    public Message getMessage(int id) throws SQLException
    {
        String query = "select * from message where messageID =?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        Message message = new Message();
        ResultSet rs = ps.executeQuery();
        boolean c = false;
        while (rs.next())
        {
            c = true;
            message.setMessageId(rs.getInt("messageID"));
            message.setText(rs.getString("post"));
            message.setSenderId(rs.getInt("senderID"));
            message.setReceiverId(rs.getInt("receiverID"));
        }
        if(c)
        {
            return message;
        }
        else 
        {
            return null;
        }
    }

    public void update(Message message) throws SQLException
    {
       String query = "update message set post = ? where messageID = ?";
       PreparedStatement ps = con.prepareStatement(query);
       ps.setString(1, message.getText());
       ps.setInt(2, message.getMessageId());
       ps.executeUpdate();
    }
}   
