package com.jdbc.main;

import java.io.*;
import java.sql.*;

/*
 * Program to get pet profile and store into
 * a csv file.
 */
public class DownloadPetProfile {

    public static void main(String[] args) {

        downloadPetProfile(1);
    }

    public static void downloadPetProfile(int petId) {
        String url = "jdbc:mysql://localhost/petcare";
        String password = "ParkSideRoad161997";
        String username = "root";

        String csvFilePath = "petProfile.csv";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "SELECT * FROM pet WHERE petID = " + petId;

            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery(sql);

            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(csvFilePath));

            // write header line containing column names
            fileWriter.write("petID,ownerID,name, breed, notes");

            while (result.next()) {
                int petId2 = result.getInt("petID");
                int ownerId = result.getInt("ownerID");
                String name = result.getString("name");
                String breed = result.getString("breed");
                String notes = result.getString("notes");

                String line = String.format("\"%d\",%d, %s, %s, %s",
                        petId2, ownerId, name, breed, notes);

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