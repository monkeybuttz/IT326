package com.jdbc.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.jdbc.model.GroomingAppointment;
import com.jdbc.model.Pet;
import com.jdbc.util.JDBCConnection;

public class PetImp {
    static Connection con = JDBCConnection.getConnection();

     
    public int add(Pet pet) throws SQLException {
        String query = "INSERT into pet(ownerID, name, breed, notes, image) VALUES (?, ?, ?, ?, ? )";
        PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, pet.getOwnerId());
        ps.setString(2, pet.getName());
        ps.setString(3, pet.getBreed());
        ps.setString(4, pet.getNotes());
        ps.setBlob(5, pet.getImage());
        ps.executeUpdate();
        int id = -1;
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            id = rs.getInt(1);
        }
        return id;
    }

     
    public void delete(int id) throws SQLException {
        String query = "delete from pet where petID =?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

     
    public Pet getPet(int id) throws SQLException {
        String query = "select * from pet where petID =?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        Pet pet = new Pet();
        ResultSet rs = ps.executeQuery();
        boolean c = false;
        while (rs.next()) {
            c = true;
            pet.setPetId(rs.getInt("petID"));
            pet.setOwnerId(rs.getInt("ownerID"));
            pet.setName(rs.getString("name"));
            pet.setBreed(rs.getString("breed"));
            pet.setNotes(rs.getString("notes"));
            pet.setImage(rs.getBlob("image"));
        }

        query = "select * from groomingappointment where petID = ?";
        PreparedStatement ps2 = con.prepareStatement(query);
        ps2.setInt(1, id);
        ResultSet rs2 = ps2.executeQuery();
        List<GroomingAppointment> ls = new ArrayList<GroomingAppointment>();
        while (rs2.next()) {
            GroomingAppointment gapt = new GroomingAppointment();
            gapt.setAptId(rs2.getInt("aptID"));
            gapt.setGroomerId(rs2.getInt("groomerID"));
            gapt.setPetId(rs2.getInt("petID"));
            gapt.setAptDate(rs2.getString("date"));
            gapt.setLocation(rs2.getString("location"));
            gapt.setNotes(rs2.getString("notes"));
            ls.add(gapt);
        }
        pet.setGroomingAppointments(ls);
        if (c) {
            return pet;
        } else {
            return null;
        }
    }

     
    public List<Pet> getPets(int ownerID) throws SQLException {
        String query = "select * from pet where ownerID = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, ownerID);
        ResultSet rs = ps.executeQuery();
        List<Pet> ls = new ArrayList<Pet>();

        while (rs.next()) {
            Pet pet = new Pet();
            pet.setPetId(rs.getInt("petID"));
            pet.setOwnerId(rs.getInt("ownerID"));
            pet.setName(rs.getString("name"));
            pet.setBreed(rs.getString("breed"));
            pet.setNotes(rs.getString("notes"));
            pet.setImage(rs.getBlob("image"));
            ls.add(pet);
        }
        return ls;
    }

     
    public void update(Pet pet) throws SQLException {
        String query = "update pet set name = ?, breed = ?, notes = ?, image = ? where petID = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, pet.getName());
        ps.setString(2, pet.getBreed());
        ps.setString(3, pet.getNotes());
        ps.setBlob(4, pet.getImage());
        ps.setInt(5, pet.getPetId());
        ps.executeUpdate();
    }
}
