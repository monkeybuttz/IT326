package com.springboot.pathControllers;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import com.google.gson.Gson;
import com.jdbc.dao.PetImp;
import com.jdbc.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.jdbc.util.JDBCConnection;

@RestController
public class PetController {
    static Connection con;

    @Autowired
    public PetController() {
        con = JDBCConnection.getConnection();
    }


    @GetMapping("/pet/{id}")
    public String getPet(@PathVariable int id) throws SQLException {
        String query = "select * from pet where petID = ?";
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
            pet.setImage(rs.getString("image"));
        }

        query = "select * from groomingappointment where petID = ?";
        ps = con.prepareStatement(query);
        ps.setInt(1, id);
        rs = ps.executeQuery();
        List<GroomingAppointment> ls = new ArrayList<GroomingAppointment>();
        while (rs.next()) {
            GroomingAppointment gapt = new GroomingAppointment();
            c = true;
            gapt.setAptId(rs.getInt("aptID"));
            gapt.setGroomerId(rs.getInt("groomerID"));
            gapt.setPetId(rs.getInt("petID"));
            gapt.setAptDate(rs.getString("date"));
            gapt.setLocation(rs.getString("location"));
            gapt.setNotes(rs.getString("notes"));
            gapt.setFavorited(rs.getBoolean("favorited"));
            ls.add(gapt);
        }
        pet.setGroomingAppointments(ls);

        query = "select * from petdocument where petID = ?";
        ps = con.prepareStatement(query);
        ps.setInt(1, id);
        rs = ps.executeQuery();
        List<String> pics = new ArrayList<String>();
        while (rs.next()) {
            c = true;
            pics.add(rs.getString("uri"));
        }
        pet.setDocuments(pics);

        if (c) {
            return new Gson().toJson(pet);
        } else {
            return null;
        }
    }

    @PostMapping("/pet/{id}")
    public String updatePet(@RequestBody Pet pet, @PathVariable int id) throws SQLException {
        String query = "update pet set name = ?, breed = ?, notes = ?, image = ? where petID = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, pet.getName());
        ps.setString(2, pet.getBreed());
        ps.setString(3, pet.getNotes());
        ps.setString(4, pet.getImage());
        ps.setInt(5, id);
        ps.executeUpdate();

         query = "delete from petdocument where petID =?";
        ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();

        for (String doc : pet.getDocuments()) {
            query = "INSERT into PetDocument(petID, uri) VALUES ( ?, ? ) ";
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ps.setString(2, doc);
            ps.executeUpdate();
        }

        return new Gson().toJson(pet);

    }
    
    @PostMapping("/pet/add/{userID}")
    public String addPet(@RequestBody Pet pet, @PathVariable int userID) throws SQLException {
        String query = "INSERT into pet(ownerID, name, breed, notes, image) VALUES (?, ?, ?, ?, ? )";
        PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, userID);
        ps.setString(2, pet.getName());
        ps.setString(3, pet.getBreed());
        ps.setString(4, pet.getNotes());
        ps.setString(5, pet.getImage());
        ps.executeUpdate();
        int id = -1;
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            id = rs.getInt(1);
        }
        return new Gson().toJson(id);

    }

    @GetMapping("/pets/{userID}")
    public String getPets(@PathVariable int userID) throws SQLException {
        User u = new Groomer();
        u.setID(userID);
        String query = "";
        if(u.readAccount().isGroomer == 1){
            query = "select p.* from pet p, groomingAppointment ga Where p.petID = ga.petID and ga.groomerID = ?";
        } else {
            query = "select * from pet where ownerID = ?";
        }
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, userID);
        ResultSet rs = ps.executeQuery();
        List<Pet> ls = new ArrayList<Pet>();

        while (rs.next()) {
            Pet pet = new Pet();
            pet.setPetId(rs.getInt("petID"));
            pet.setOwnerId(rs.getInt("ownerID"));
            pet.setName(rs.getString("name"));
            pet.setBreed(rs.getString("breed"));
            pet.setNotes(rs.getString("notes"));
            pet.setImage(rs.getString("image"));
            ls.add(pet);
        }
        return new Gson().toJson(ls);
    }

    @GetMapping("/pet/delete/{id}")
    public String deletePet(@PathVariable int id) throws SQLException {

        String query = "delete from pet where petID =?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
        return new Gson().toJson("success");
    }
    

}
