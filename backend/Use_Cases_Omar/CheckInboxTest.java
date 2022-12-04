package checkinbox;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import org.junit.Assert;
import org.junit.Test;

public class CheckInboxTest {

	@Test
	public void emptyInboxTest() {
		
		var inbox = new CheckInbox();
		assertNotNull(inbox);
	}
	
	//Test to get column names
	@Test
	public void getColumnNames() {
		
		String url = "jdbc:mysql://localhost/petcare";
		String password = "ParkSideRoad161997";
		String username = "root";
		
		Connection con = null;
		PreparedStatement p = null;
        ResultSet rs = null;
		
		String sql2 = "SELECT * FROM message";
		
		
		try {
			con = DriverManager.getConnection(url, username, password);
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
	
	//test for senderID and receiver ID that dont have any
	// messages associated with them
	@Test
	public void userDoesNotHaveMessages(){
		
		String url = "jdbc:mysql://localhost/petcare";
		String password = "ParkSideRoad161997";
		String username = "root";
		
		Connection con = null;
		PreparedStatement p = null;
        ResultSet rs = null;
		
        
		String sql2 = "SELECT * FROM message WHERE "
				+ "senderID = 3 OR receiverID = 3";
		
		try 
		{
			con = DriverManager.getConnection(url, username, password);
			p = con.prepareStatement(sql2);
			rs = p.executeQuery(sql2);
//			ResultSetMetaData rsmd;
//	        rsmd = rs.getMetaData();
			String post = null;
			String line = null;
			while (rs.next()) {
				post = rs.getString("post");
				line = String.format("%s", post);
				assertEquals(" " , line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//Test case to see if all the messageID's are consistent
	@Test
	public void matchMessageId(){
		
		//establish jdbc connection
		String url = "jdbc:mysql://localhost/petcare";
		String password = "ParkSideRoad161997";
		String username = "root";
		
		Connection con = null;
		PreparedStatement p = null;
        ResultSet rs = null;
		
		String sql2 = "SELECT * FROM message WHERE "
				+ "senderID = 1 OR receiverID = 1";
		
		try 
		{
			con = DriverManager.getConnection(url, username, password);
			p = con.prepareStatement(sql2);
			rs = p.executeQuery(sql2);
			//array to store messageID's of each row
			int[] msgId = new int[4];
			//expected output array
			int numbers[] = {1, 2, 3, 4};
			int i = 0;
			//get results of column messageID from each row
			while (rs.next()) {
				msgId[i] = rs.getInt("messageID");
				i++;
			}
			// test to see if numbers[] and msgIds[] match
//			assertEquals(numbers , msgId);
			Assert.assertArrayEquals(msgId, numbers);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
