package com.jdbc.main;

import java.io.*;
import java.sql.*;

import com.jdbc.util.JDBCConnection;
/*
 * Program to get pet profile and store into
 * a csv file.
 */
public class DownloadPetProfile {
 
    public static void main(String[] args) {
        
    	downloadPetProfile(1);
    }
    
    public static String downloadPetProfile(int petId)
    {
        String linne = " ";
        String csvFilePath = "petProfile.csv";
         
        try {
        	
        	Connection con = JDBCConnection.getConnection();
            String sql = "SELECT * FROM pet WHERE petID = " + petId;
             
            Statement statement = con.createStatement();
             
            ResultSet result = statement.executeQuery(sql);
             
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(csvFilePath));
             
            // write header line containing column names       
            fileWriter.write("petID,ownerID,name, breed, notes");
//            static String line = " ";
             
            while (result.next()) {
                int petId2 = result.getInt("petID");
                int ownerId = result.getInt("ownerID");
                String name = result.getString("name");
                String breed = result.getString("breed");
                String notes = result.getString("notes");
                 
                String line2 = String.format("%d,%d, %s, %s, %s",
                        petId2, ownerId, name, breed, notes);
                System.out.println(line2);
                linne = linne + line2;
                
                 
                fileWriter.newLine();
                fileWriter.write(line2);
//                return line2;
            }
             
            statement.close();
            fileWriter.close();
             
        } catch (SQLException e) {
            System.out.println("Datababse error:");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("File IO error:");
            e.printStackTrace();
        }
//    	return 0;
//        System.out.println(line);
//        return 0;
        return linne;
    }
 
}
