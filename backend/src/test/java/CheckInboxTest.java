
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.*;

import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Before;
import org.junit.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.jdbc.main.CheckInbox;
import com.jdbc.util.JDBCConnection;



public class CheckInboxTest {
	

	//Test to check database connectivity
	@BeforeAll
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
		
		String words = " Hi there groomer\r\n"
				+ "Hello there user\r\n"
				+ "I want my pet groomed\r\n"
				+ "Ok come see me\r\n";
		
		var inbox = new CheckInbox();
		assertEquals(words, inbox.checkInbox(1, 1));
	}
	
}



