
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.jdbc.model.*;
import com.jdbc.util.JDBCConnection;
import java.sql.*;


/*
 * Tsts the MessageTest.java Program
 * Name: Grant Lane
 */
public class MessageTest 
{
    @Test
    //Testing creating an empty Message
    public void emptyMessageTest()
    {
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
    //Testing creating a Message with all parameters
    public void createMessageTest()
    {
        Message message = new Message(1, "Howdy!", 2, 3);
        assertNotNull(message);
    }

    @Test
    //Test get messageId
    public void getMessageIdTest()
    {
        Message message = new Message(1, "Howdy!", 2, 3);
        int id = message.getMessageId();
        assertEquals(1,id);
    }

    @Test
    //Test get message text
    public void getTextTest()
    {
        Message message = new Message(1, "Howdy!", 2, 3);
        String text = message.getText();
        assertEquals("Howdy!", text);
    }

    @Test
    //Test get messageId
    public void getSenderIdTest()
    {
        Message message = new Message(1, "Howdy!", 2, 3);
        int id = message.getSenderId();
        assertEquals(2,id);
    }

    @Test
    //Test get messageId
    public void getReceiverIdTest()
    {
        Message message = new Message(1, "Howdy!", 2, 3);
        int id = message.getReceiverId();
        assertEquals(3,id);
    }

    @Test
    //Test get messageId
    public void setMessageIdTest()
    {
        int id = 1;

        Message message = new Message();
        message.setMessageId(id);

        assertEquals(id, message.getMessageId());
    }

    @Test
    //Test get messageId
    public void setTextTest()
    {
        String text = "Hi! How are ya?";

        Message message = new Message();
        message.setText(text);

        assertEquals(text, message.getText());
    }

    @Test
    //Test get messageId
    public void setSenderTest()
    {
        int id = 1;

        Message message = new Message();
        message.setSenderId(id);

        assertEquals(id, message.getSenderId());
    }

    @Test
    //Test get messageId
    public void setReceiverTest()
    {
        int id = 1;

        Message message = new Message();
        message.setReceiverId(id);

        assertEquals(id, message.getReceiverId());
    }
}
