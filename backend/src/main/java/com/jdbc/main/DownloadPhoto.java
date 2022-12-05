package com.jdbc.main;

import java.io.*;
import java.sql.*;

import com.jdbc.util.JDBCConnection;

/*
 * Program to download photo with parameter imageId
 * Image will be stored in the src file. 
 */

public class DownloadPhoto {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	
	public static int downloadPhoto(int imageId)
	{
		
		Connection con = null;
		PreparedStatement p = null;
        	ResultSet rs = null;
        
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
        		con = JDBCConnection.getConnection();
			p=con.prepareStatement("select * from image where id = " + imageId);
			rs=p.executeQuery();
			if(rs.next()){
				File file=new File("src\\image1.png");
				FileOutputStream fos=new FileOutputStream(file);
				byte b[];
				Blob blob;
				blob=rs.getBlob("image");
				b=blob.getBytes(1,(int)blob.length());
				fos.write(b);
				fos.close();

				p.close();
				con.close();
				return 1;
			}else {
				System.out.println("Image not found.");
				return 0;
			}

//			p.close();
//			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}	
		return 0;
	}
}
