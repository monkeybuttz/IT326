import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class MessageController
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
        int id = getId(senderID, receiverID, text);
        message.setMessageId(id);
    }

    private int getId(int senderId, int recieverId, String text) throws SQLException
    {
        String query = "Select messageID from message where senderID = ? AND receiverID = ? AND post = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, senderId);
        ps.setInt(2, recieverId);
        ps.setString(3, text);
        ResultSet rs = ps.executeQuery();
        if (rs.next())
        {
            int id = rs.getInt("messageID");
            return id;
        }
        else
            return 0;
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

    public List<Message> getSentMessages(int senderID) throws SQLException
    {
        List<Message> ls = new ArrayList<Message>();
        String query = "select * from Message where senderID = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, senderID);
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            Message message = new Message();
            message.setMessageId(rs.getInt("messageID"));
            message.setReceiverId(rs.getInt("receiverID"));
            message.setSenderId(senderID);
            message.setText(rs.getString("post"));
            ls.add(message);
        }
        return ls;
    }

    public List<Message> getRecievedMessages(int receiverID) throws SQLException
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
        return ls;
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
