package petcare;

import java.io.*;
import java.sql.*;

/*
 * Program to download photo with parameter imageId
 * Image will be stored in the src file. 
 */

public class DownloadPhoto {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		downloadPhoto(2);
	}
	
	public static void downloadPhoto(int imageId)
	{
		String url = "jdbc:mysql://localhost/petcare";
		String password = "ParkSideRoad161997";
		String username = "root";
		
		PreparedStatement p = null;
        ResultSet rs = null;
        
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, username, password);
			File file=new File("src\\image1.png");
			FileOutputStream fos=new FileOutputStream(file);
			byte b[];
			Blob blob;
			p=con.prepareStatement("select * from image where id = " + imageId);
			rs=p.executeQuery();
			while(rs.next()){
				blob=rs.getBlob("image");
				b=blob.getBytes(1,(int)blob.length());
				fos.write(b);
			}
			p.close();
			fos.close();
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}	
	}
}
