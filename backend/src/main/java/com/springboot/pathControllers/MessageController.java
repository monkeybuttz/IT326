import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class MessageController
{
    static Connection con = JDBCConnection.getConnection();

    public void sendMessage(Message message) throws SQLException
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
    }

    @DeleteMapping
    public void delete(@PathVariable int id) throws SQLException
    {
        String query = "delete from message where messageID =?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    @GetMapping
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

    @GetMapping("/messages/inbox/{recieverID}")
    public String getRecievedMessages(@PathVariable int receiverID) throws SQLException
    {
        List<Message> ls = new ArrayList<Message>();
        String query = "select * from Message where receiverID = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, receiverID);
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            Message message = new Message();
            message.setMessageId(rs.getInt("messageID"));
            message.setReceiverId(receiverID);
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
