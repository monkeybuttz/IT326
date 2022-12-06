package com.springboot.pathControllers;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties.Credential;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import com.jdbc.dao.PetImp;
import com.jdbc.model.*;
import com.google.gson.Gson;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.jdbc.util.JDBCConnection;

import java.io.*;

@RestController
public class MessageController
{
    
    static Connection con;
    static Gson gson;

    @Autowired
    public MessageController() {
        con = JDBCConnection.getConnection();
        gson = new Gson();
    }


    @PostMapping("/message/send")
    public String sendMessage(@RequestBody Message message) throws SQLException
    {

        String text = message.getText();
        int senderID = message.getSenderId();
        int receiverID = message.getReceiverId();

        String query = "INSERT into message(post, senderID, receiverID) VALUES (?, ?, ?)";

        PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, text);
        ps.setInt(2, senderID);
        ps.setInt(3, receiverID);
        ps.executeUpdate();
        int id = -1;
        ResultSet rs = ps.getGeneratedKeys();
        if(rs.next())
        {
            id = rs.getInt(1);
        }
        message.setMessageId(id);

        return new Gson().toJson("Success");
    }

    @DeleteMapping("/message/{id}")
    public void delete(@PathVariable int id) throws SQLException
    {
        String query = "delete from message where messageID =?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    @GetMapping("/message/{id}")
    public Message getMessage(@PathVariable int id) throws SQLException
    {
        String query = "select * from message where messageID =?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        Message message = new Message();
        ResultSet rs = ps.executeQuery();
        boolean c = false;
        while (rs.next()) {
            c = true;
            message.setMessageId(rs.getInt("messageID"));
            message.setText(rs.getString("post"));
            message.setSenderId(rs.getInt("senderID"));
            message.setReceiverId(rs.getInt("receiverID"));
        }
        if (c) {
            return message;
        } else {
            return null;
        }
    }

    @GetMapping("/messages/sent/{senderID}")
    public String getSentMessages(@PathVariable int senderID) throws SQLException
    {
        List<Message> ls = new ArrayList<Message>();
        String query = "select * from Message where senderID = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, senderID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Message message = new Message();
            message.setMessageId(rs.getInt("messageID"));
            message.setReceiverId(rs.getInt("receiverID"));
            message.setSenderId(senderID);
            message.setText(rs.getString("post"));
            ls.add(message);
        }
        return gson.toJson(ls);
    }

    @GetMapping("/message/inbox/{id}")
    public String getRecievedMessages(@PathVariable int id) throws SQLException
    {
        List<Message> ls = new ArrayList<Message>();
        String query = "select * from Message where receiverID = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            Message message = new Message();
            message.setMessageId(rs.getInt("messageID"));
            message.setReceiverId(id);
            message.setSenderId(rs.getInt("senderID"));
            message.setText(rs.getString("post"));
            ls.add(message);
        }
        return gson.toJson(ls);
    }


    @PostMapping("/message")
    public void update(@RequestBody Message message) throws SQLException
    {
       String query = "update message set post = ? where messageID = ?";
       PreparedStatement ps = con.prepareStatement(query);
       ps.setString(1, message.getText());
       ps.setInt(2, message.getMessageId());
       ps.executeUpdate();
       gson.toJson("success");
    }
}   
