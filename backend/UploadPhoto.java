package petcare;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


public class UploadPhoto {
	
	public static void main(String[] args)
	{
		String url = "jdbc:mysql://localhost/petcare";
		String password = "ParkSideRoad161997";
		String username = "root";
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, username, password);
			String query = "Insert into image values (?, ?)";
			PreparedStatement stmt = con.prepareStatement(query);
			
			JFileChooser jfc = new JFileChooser();
			jfc.showOpenDialog(null);
			File file = jfc.getSelectedFile();
			
			FileInputStream fis = new FileInputStream(file);
			stmt.setInt(1, 1);
			stmt.setBinaryStream(2, fis,fis.available());
			
			stmt.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Image successfully added.");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
