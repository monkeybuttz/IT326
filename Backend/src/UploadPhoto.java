package backend.src;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class UploadPhoto {

	public static void main(String[] args) {
		photoAdd(2);
	}

	public static void photoAdd(int imageId) {
		String url = "jdbc:mysql://localhost/petcare";
		String password = "ParkSideRoad161997";
		String username = "root";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, username, password);
			String query = "Insert into image values (?, ?)";
			PreparedStatement stmt = con.prepareStatement(query);

			JFileChooser jfc = new JFileChooser();
			jfc.showOpenDialog(null);
			File file = jfc.getSelectedFile();

			FileInputStream fis = new FileInputStream(file);
			stmt.setInt(1, imageId);
			stmt.setBinaryStream(2, fis, fis.available());

			stmt.executeUpdate();

			JOptionPane.showMessageDialog(null, "Image successfully added.");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}