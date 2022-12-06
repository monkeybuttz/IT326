
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;


import com.jdbc.util.JDBCConnection;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import com.jdbc.main.DownloadPhoto;

public class DownloadPhotoTest {
	
	//Test to check database connectivity
	@BeforeClass
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
	
	@Test
	public void emptyInboxTest() {
		
		var img = new DownloadPhoto();
		assertNotNull(img);
	}
	

	//Test to see if image exists when image does not exist but id 
	//and aid exist
	@Test
	public void testForExisting() {
		var phot = new DownloadPhoto();
		assertEquals(0 , phot.downloadPhoto(2));
	}
////	
	//Test to see if image exists when image does not exist
		@Test
		public void testForNonExisting() {
			var phot = new DownloadPhoto();
			assertEquals(0 , phot.downloadPhoto(4));
		}

}
