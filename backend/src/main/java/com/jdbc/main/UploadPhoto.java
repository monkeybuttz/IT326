package com.jdbc.main;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.jdbc.util.JDBCConnection;

public class UploadPhoto {
	
	public static void main(String[] args)
	{
		
	}
	
	public static int photoAdd(int imageId)
	{
		
		Connection con = null;
		PreparedStatement p = null;
        ResultSet rs = null;
        
		try
		{
			con = JDBCConnection.getConnection();
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String query = "Insert into image values (?, ?)";
			String query2 = "Select * FROM image WHERE id = " + imageId;
			p = con.prepareStatement(query2);
			rs = p.executeQuery();
			
			if(rs.next()) {
				System.out.println("Photo with imageID already exists. "
						+ "Please choose a different value for value 'imageID'.");
				return 0;
			}else {
				PreparedStatement stmt = con.prepareStatement(query);
				
				JFileChooser jfc = new JFileChooser();
				jfc.showOpenDialog(null);
				File file = jfc.getSelectedFile();
				
				FileInputStream fis = new FileInputStream(file);
				stmt.setInt(1, imageId);
				stmt.setBinaryStream(2, fis,fis.available());
				
				stmt.executeUpdate();
				
				JOptionPane.showMessageDialog(null, "Image successfully added.");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return 1;
	}
}
