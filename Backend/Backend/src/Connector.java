/*
 * Template how to connect to SQL
 */
import java.io.*;
import java.sql.*;

public class Connector {
    public static void main(String[] args) throws Exception {
        // petcare is the name of the database i used to test it
        // you will need to a create one base off of it
        String url = "jdbc:mysql://localhost:3306/PetCare";

        String username = "root";
        //Password is for your workbench installation
        String password = "PetCare.it326";
        
        Connection con = DriverManager.getConnection(url, username, password);
        System.out.println("Connection Established Successfully");
        
        
        con.close();
        System.out.println("Connection Closed");
    }
}
