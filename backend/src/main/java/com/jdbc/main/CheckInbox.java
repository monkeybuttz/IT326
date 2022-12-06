package com.jdbc.main;

import com.jdbc.util.JDBCConnection;
import java.sql.*;
import java.io.*;

/*	program to get all messages related to user and 
		stores into a csv file.  
	*/
public class CheckInbox {

    public static void main(String[] args) {

    }

    public static String checkInbox(int senderID, int recieverID) {

        String linne = " ";

        Connection con = null;
        PreparedStatement p = null;
        ResultSet rs = null;

        String csvFilePath = "inbox.csv";

        try {

            con = JDBCConnection.getConnection();
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(csvFilePath));

            // write header line containing column names
            fileWriter.write("Message");
            System.out.println("Message");

            String sql2 = "select * from message where senderID = ? OR receiverID = ?";
            p = con.prepareStatement(sql2);
            p.setInt(1, senderID);
            p.setInt(2, recieverID);
            rs = p.executeQuery();

            while (rs.next()) {
                String post = rs.getString("post");
                linne += post + System.lineSeparator();
                System.out.println(post);

                fileWriter.newLine();
                fileWriter.write(post);
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
        return linne;
    }

}
