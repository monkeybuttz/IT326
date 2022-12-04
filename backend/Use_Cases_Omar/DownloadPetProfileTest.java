package downloadpetprofile;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import org.junit.Test;

import checkinbox.CheckInbox;

public class DownloadPetProfileTest {
	
	@Test
	public void emptyInboxTest() {
		
		var inbox = new CheckInbox();
		assertNotNull(inbox);
	}

	@Test
	public void getColumnNames() {
		
		String url = "jdbc:mysql://localhost/petcare";
		String password = "ParkSideRoad161997";
		String username = "root";
		
		Connection con = null;
		PreparedStatement p = null;
        ResultSet rs = null;
		
        String sql2 = "SELECT * FROM pet";
        
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
	        String columnName5 = rsmd.getColumnName(5);
	        String columnName6 = rsmd.getColumnName(6);
	        
	        assertEquals("petID", columnName1);
	        assertEquals("ownerID", columnName2);
	        assertEquals("name", columnName3);
	        assertEquals("breed", columnName4);
	        assertEquals("notes", columnName5);
	        assertEquals("image", columnName6);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
