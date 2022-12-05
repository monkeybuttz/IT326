
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import com.jdbc.util.JDBCConnection;
//import static org.junit.Assert.*;
import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import org.junit.jupiter.api.Test;

import com.jdbc.main.DownloadPetProfile;

//import checkinbox.CheckInbox;

public class DownloadPetProfileTest {

	//Test to check database connectivity
	@Test
	public void getColumnNames() {
		
		Connection con = null;
		PreparedStatement p = null;
        ResultSet rs = null;
		
        String sql2 = "SELECT * FROM pet";
        
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
	
	@Test
	public void emptyInboxTest() {
		
		var pet = new DownloadPetProfile();
		assertNotNull(pet);
	}
	
	//Test to check for the correct data.
	@Test
	public void checkDataForExistingPetId() {
		
		var pet = new DownloadPetProfile();
		String line = " 1,1, jojo, dutch shepherd, Friendly "
						+ "and active. Is trained";
		assertEquals(line, pet.downloadPetProfile(1));
	}
	
	//Test to check for pet ID that does not exist.
	@Test
	public void checkDataForNullPetId() {
			
		var pet = new DownloadPetProfile();
		assertEquals(" ", pet.downloadPetProfile(2));
	}

}
