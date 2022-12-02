import java.sql.*;

public class Driver
{
    public static void main(String[] args) throws SQLException
    {
        Message message = new Message();
        message.setMessageId(001);
        message.setReceiverId(002);
        message.setText("Hello! My Name is Grant Lane. Can you Groom my Pet?");
        message.setSenderId(004);


        Connection con = JDBCConnection.getConnection();
        String query1 = "INSERT into User (email, isGroomer, name, password, phoneNum, userID, username) VALUES (?, ?, ?, ?, ?, ?, ?)";

        //Insert receiver
        PreparedStatement ps1 = con.prepareStatement(query1);
        ps1.setString(1, "gclane1@ilstu.edu");
        ps1.setBoolean(2, false);
        ps1.setString(3, "Grant Lane");
        ps1.setString(4, "802840906");
        ps1.setInt(5, 8307033);
        ps1.setInt(6, 002);
        ps1.setString(7, "gclane1");
        ps1.executeUpdate();

        //Insert Sender
        String query2 = "INSERT into User (email, isGroomer, name, password, phoneNum, userID, username) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps2 = con.prepareStatement(query2);
        ps2.setString(1, "jpdoe1@ilstu.edu");
        ps2.setBoolean(2, true);
        ps2.setString(3, "John Doe");
        ps2.setString(4, "80643221");
        ps2.setInt(5, 3770514);
        ps2.setInt(6, 004);
        ps2.setString(7, "jpdoe1");
        ps2.executeUpdate();

        /* 
        String pullReceiver = "Select userID from user where userID  = ?";
        PreparedStatement prepareReceiver = con.prepareStatement(pullReceiver);
        prepareReceiver.setInt(1, 002);
        ResultSet resultReceiver = prepareReceiver.executeQuery();
        message.setReceiverId(resultReceiver.getInt("receiverID"));

        String pullSender = "Select userID from user where userID  = ?";
        PreparedStatement prepareSender = con.prepareStatement(pullSender);
        prepareSender.setInt(1, 004);
        ResultSet resultSender = prepareSender.executeQuery();
        message.setSenderId(resultSender.getInt("senderID"));
        */
        
        MessageImpl messageDAO = new MessageImpl();
        messageDAO.add(message);
        
        Message copy = messageDAO.getMessage(1);

        System.out.println("\nThe Message Contents:");
        System.out.println("Message Id: " + message.getMessageId());
        System.out.println("Receiver Id: " + message.getReceiverId());
        System.out.println("Sender Id: " + message.getSenderId());
        System.out.println("Text:\t" + message.getText());

        System.out.println("\nThe Copied Message Contents:");
        System.out.println("Message Id: " + copy.getMessageId());
        System.out.println("Receiver Id: " + copy.getReceiverId());
        System.out.println("Sender Id: " + copy.getSenderId());
        System.out.println("Text:\t" + copy.getText());
        
        String newText = "Thank you so much! How much do I owe you?";
        message.setText(newText);
        messageDAO.update(message);
        System.out.println("\nUpdated Message Text:\t"+ message.getText());
    }
}
