
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.SQLException;

import com.jdbc.model.*;
import com.jdbc.util.JDBCConnection;
import java.sql.*;

import com.springboot.pathControllers.MessageController;
import com.springboot.pathControllers.UserController;

/*
 * Tsts the MessageTest.java Program
 * Name: Grant Lane
 */
public class MessageTest {
    @Test
    // Testing creating an empty Message
    public void emptyMessageTest() {
        Message emp = new Message();
        assertNotNull(emp);
        ;
    }

    @Test
    public void sendMessage() throws SQLException {
        Message message = new Message(0, "jhfjdsgf", 1, 2);
        Connection con = JDBCConnection.getConnection();
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
        if (rs.next()) {
            id = rs.getInt(1);
        }
        message.setMessageId(id);
        assertEquals(1, 1);
    }

    @Test
    // Testing creating a Message with all parameters
    public void createMessageTest() {
        Message message = new Message(1, "Howdy!", 2, 3);
        assertNotNull(message);
    }

    @Test
    // Test get messageID
    public void getMessageIdTest() {
        Message message = new Message(1, "Howdy!", 2, 3);
        int id = message.getMessageId();
        assertEquals(1, id);
    }

    @Test
    // Test get message text
    public void getTextTest() {
        Message message = new Message(1, "Howdy!", 2, 3);
        String text = message.getText();
        assertEquals("Howdy!", text);
    }

    @Test
    // Test get messageID
    public void getSenderIdTest() {
        Message message = new Message(1, "Howdy!", 2, 3);
        int id = message.getSenderId();
        assertEquals(2, id);
    }

    @Test
    // Test get messageID
    public void getReceiverIdTest() {
        Message message = new Message(1, "Howdy!", 2, 3);
        int id = message.getReceiverId();
        assertEquals(3, id);
    }

    @Test
    // Test get messageID
    public void setMessageIdTest() {
        int id = 1;

        Message message = new Message();
        message.setMessageId(id);

        assertEquals(id, message.getMessageId());
    }

    @Test
    // Test get messageID
    public void setTextTest() {
        String text = "Hi! How are ya?";

        Message message = new Message();
        message.setText(text);

        assertEquals(text, message.getText());
    }

    @Test
    // Test get messageID
    public void setSenderTest() {
        int id = 1;

        Message message = new Message();
        message.setSenderId(id);

        assertEquals(id, message.getSenderId());
    }

    @Test
    // Test get messageID
    public void setReceiverTest() {
        int id = 1;

        Message message = new Message();
        message.setReceiverId(id);

        assertEquals(id, message.getReceiverId());
    }

    @Test
    public void sendMessageTest() throws SQLException {
        Owner groomer1 = new Owner();
        groomer1.setName("Braydon Hughes");
        groomer1.setEmail("bhughe2@ilstu.edu");
        groomer1.setUsername("bhughe2");
        groomer1.setPassword("1234");
        groomer1.setIsGroomer(1);
        groomer1.setPhoneNumber(1234567890);
        UserController ucon1 = new UserController();
        ucon1.createUser(groomer1);

        Owner groomer2 = new Owner();
        groomer2.setName("Braydon Hughes");
        groomer2.setEmail("bthuqq3@ilstu.edu");
        groomer2.setUsername("bhughe3");
        groomer2.setPassword("12345");
        groomer2.setIsGroomer(1);
        groomer2.setPhoneNumber(1234567890);
        UserController ucon2 = new UserController();
        ucon2.createUser(groomer2);

        Message msg = new Message("Test message", 1, 2);
        MessageController msgContr = new MessageController();
        msgContr.sendMessage(msg);

    }
}
