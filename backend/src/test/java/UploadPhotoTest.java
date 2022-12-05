package backend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import org.junit.jupiter.api.Test;

import com.jdbc.util.JDBCConnection;

import com.jdbc.main.UploadPhoto;

public class UploadPhotoTest {

	// Test to check database connectivity
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
			
			var inbox = new UploadPhoto();
			assertNotNull(inbox);
		}
		
	//program runs but click close
	@Test
	public void AddImageWithNewId() {
		
		var img = new UploadPhoto();
		assertEquals(1, img.photoAdd(5));
	}
	
	@Test
	public void AddImageWithExistingId() {
		
		var img = new UploadPhoto();
		assertEquals(0, img.photoAdd(1));
	}

}
