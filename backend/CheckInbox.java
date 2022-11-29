package petcare;

import java.sql.*;

public class CheckInbox {
 
    // Step1: Main driver method
    public static void main(String[] args)
    {
    	String url = "jdbc:mysql://localhost/petcare";
		String password = "ParkSideRoad161997";
		String username = "root";
        // Step 2: Making connection using
        // Connection type and inbuilt function on
//        Connection con = null;
        PreparedStatement p = null;
        ResultSet rs = null;
 
        // Try block to catch exception/s
        try {
        	Connection con = DriverManager.getConnection(url, username, password);
            // SQL command data stored in String datatype
            String sql = "select * from inbox";
            p = con.prepareStatement(sql);
            rs = p.executeQuery();
 
            // Printing ID, name, email of customers
            // of the SQL command above
//            System.out.println("inboxId");
            int inboxId = 0;
 
            // Condition check
            while (rs.next()) {
 
                inboxId = rs.getInt("InboxId");
//                System.out.println(inboxId);
            }
            
            String sql2 = "select * from message where inboxId = " + inboxId; 
            p = con.prepareStatement(sql2);
            rs = p.executeQuery();
         
            // Printing ID, name, email of customers
            // of the SQL command above
            System.out.println("Inbox:");
            
        }
 
        // Catch block to handle exception
        catch (SQLException e) {
 
            // Print exception pop-up on screen
            System.out.println(e);
        }
    }
}