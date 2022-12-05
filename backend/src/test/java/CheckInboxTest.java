
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.*;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;

import com.jdbc.main.CheckInbox;
import com.jdbc.util.JDBCConnection;
//import org.junit.Before;



public class CheckInboxTest {
	
	//Test to check database connectivity
	@Test
	public void getColumnNames() {
		
		Connection con = null;
		PreparedStatement p = null;
        ResultSet rs = null;
		
		String sql2 = "SELECT * FROM message";
		
		
		try {
			con = JDBCConnection.getConnection();
			p = con.prepareStatement(sql2);
			rs = p.executeQuery(sql2);
	        ResultSetMetaData rsmd;
	        rsmd = rs.getMetaData();
	        String columnName1 = rsmd.getColumnName(1);
	        String columnName2 = rsmd.getColumnName(2);
	        String columnName3 = rsmd.getColumnName(3);
	        String columnName4 = rsmd.getColumnName(4);
	        
	        assertEquals("messageID", columnName1);
	        assertEquals("post", columnName2);
	        assertEquals("senderID", columnName3);
	        assertEquals("receiverID", columnName4);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Check method is not null
	@Test
	public void emptyInboxTest() {
		
		var inbox = new CheckInbox();
		assertNotNull(inbox);
	}
	
	//test for senderID and receiver ID that dont have any
	// messages associated with them. Will receive blank csv file
	@Test
	public void userDoesNotHaveMessages(){
		
		var inbox = new CheckInbox();
		assertEquals(" ", inbox.checkInbox(3, 3));
	}
	
	//test for user that has messages associated with them
	// will return csv file containing all messages associated
	// with them.
	@Test
	public void userDoesHaveMessages(){
		
		String words = " Hi Bob are you free to groom my pet\r\n"
				+ "Hi John, yes I am free to groom your pet\r\n"
				+ "Sounds good when should we make an apt?\r\n"
				+ "Whenever you feel works best.\r\n";
		
		var inbox = new CheckInbox();
		assertEquals(words, inbox.checkInbox(1, 1));
	}
	
}


