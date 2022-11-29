package petcare;

import java.io.*;
import java.sql.*;
 
public class DownloadPetProfile {
 
    public static void main(String[] args) {
        
    	String url = "jdbc:mysql://localhost/petcare";
		String password = "ParkSideRoad161997";
		String username = "root";
         
        String csvFilePath = "Reviews-export.csv";
         
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "SELECT * FROM pet";
             
            Statement statement = connection.createStatement();
             
            ResultSet result = statement.executeQuery(sql);
             
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(csvFilePath));
             
            // write header line containing column names       
            fileWriter.write("petID,ownerID,name,breed,notes,image");
             
            while (result.next()) {
                int petId = result.getInt("petID");
                int ownerId = result.getInt("ownerID");
//                float rating = result.getFloat("rating");
//                Timestamp timestamp = result.getTimestamp("timestamp");
//                String comment = result.getString("comment");
                 
//                if (comment == null) {
//                    comment = "";   // write empty value for null
//                } else {
//                    comment = "\"" + comment + "\""; // escape double quotes
//                }
                 
                String line = String.format("\"%d\",%d",
                        petId, ownerId);
                 
                fileWriter.newLine();
                fileWriter.write(line);            
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
         
    }
 
}