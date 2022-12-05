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
            pet.setImage(rs.getBlob("image"));
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
        if (c) {
            return new Gson().toJson(pet);
        } else {
            return null;
        }
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
            pet.setImage(rs.getBlob("image"));
            ls.add(pet);
        }
        return new Gson().toJson(ls);
    }

    
    

}
