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
import com.jdbc.model.GroomingAppointment;
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
            gapt.setAptId(rs.getInt("aptID"));
            gapt.setGroomerId(rs.getInt("groomerID"));
            gapt.setPetId(rs.getInt("petID"));
            gapt.setAptDate(rs.getString("date"));
            gapt.setLocation(rs.getString("location"));
            gapt.setNotes(rs.getString("notes"));
            ls.add(gapt);
        }
        pet.setGroomingAppointments(ls);
        if (c) {
            return new Gson().toJson(pet);
        } else {
            return null;
        }
    }

    @GetMapping("/pets/{ownerID}")
    public String getPets(@PathVariable int ownerID) throws SQLException {
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
        return new Gson().toJson(ls);
    }

    
    

}
