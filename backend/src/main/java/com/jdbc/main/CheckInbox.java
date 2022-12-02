package com.jdbc.main;

import java.sql.*;
import java.io.*;
/*	program to get all messages related to user and 
		stores into a csv file.  
	*/
public class CheckInbox {
	
public static void main(String[] args) {
	
		int send = 1;
		int recieve = 1;
    	checkInbox(send, recieve);
         
	}

	public static void checkInbox(int senderId, int recieverId)
	{
		String url = "jdbc:mysql://localhost/petcare";
		String password = "ParkSideRoad161997";
		String username = "root";
		
		PreparedStatement p = null;
        ResultSet rs = null;
         
        String csvFilePath = "inbox.csv";
         
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
        	Connection con = DriverManager.getConnection(url, username, password);
          
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(csvFilePath));
            
            // write header line containing column names       
            fileWriter.write("Message");
            
            String sql2 = "select * from message where senderID = ? OR receiverID = ?"; 
            p = con.prepareStatement(sql2);
            p.setInt(1, senderId);
            p.setInt(2, recieverId);
            rs = p.executeQuery();
             
            while (rs.next()) {
//                int messageId = rs.getInt("messageID");
                String post = rs.getString("post");
                String line = String.format("%s", post);
                 
                fileWriter.newLine();
                fileWriter.write(line);            
            }
             
            p.close();
            fileWriter.close();
             
        } catch (SQLException e) {
            System.out.println("Datababse error:");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("File IO error:");
            e.printStackTrace();
        }
	}

}