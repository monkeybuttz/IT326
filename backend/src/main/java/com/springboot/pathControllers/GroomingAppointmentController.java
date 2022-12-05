package com.springboot.pathControllers;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.jdbc.dao.GroomingAppointmentImp;
import com.jdbc.model.GroomingAppointment;
import com.jdbc.util.JDBCConnection;

@RestController
public class GroomingAppointmentController {
    static Connection con;

    @Autowired
    public GroomingAppointmentController() {
        con = JDBCConnection.getConnection();
    }

    @PostMapping("/appointments/add")
    public int add(String json) throws SQLException {
        Gson gson = new GsonBuilder().create();
        GroomingAppointment apt = gson.fromJson(json, GroomingAppointment.class);
        String query = "insert into groomingappointment(groomerID, petID, date, location, notes) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, apt.getGroomerId());
        ps.setInt(2, apt.getPetId());
        ps.setString(3, apt.getDate());
        ps.setString(4, apt.getLocation());
        ps.setString(5, apt.getNotes());
        ps.executeUpdate();
        int id = -1;
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            id = rs.getInt(1);
        }
        List<Blob> blobs = apt.getImages();
        query = "insert into image (image, aid) values (?, ?)";
        if(blobs != null) {
            for(int i = 0; i < blobs.size(); i++) {
                ps = con.prepareStatement(query);
                ps.setBlob(1, blobs.get(i));
                ps.setInt(2, id);
                ps.executeUpdate();
            }
        }   
        return id;
    }
    
    @DeleteMapping("/appointments/{id}")
    public void delete(@PathVariable int id) throws SQLException {
        String query = "delete from groomingappointment where aptID =?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
        query = "delete from image where aid = ?";
        ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    @GetMapping("/appointments/{id}")
    public String getGroomingAppointment(@PathVariable int id) throws SQLException {
        String query = "select * from groomingappointment where aptID = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        GroomingAppointment gapt = new GroomingAppointment();
        ResultSet rs = ps.executeQuery();
        boolean c = false;
        while (rs.next()) {
            c = true;
            gapt.setAptId(rs.getInt("aptID"));
            gapt.setGroomerId(rs.getInt("groomerID"));
            gapt.setPetId(rs.getInt("petID"));
            gapt.setAptDate(rs.getString("date"));
            gapt.setLocation(rs.getString("location"));
            gapt.setNotes(rs.getString("notes"));
            gapt.setFavorited(rs.getBoolean("favorited"));
        }
        gapt.setImages(GroomingAppointmentImp.getAptImages(gapt.getAptId()));
        if (c) {
            return new Gson().toJson(gapt);
        } else {
            return null;
        }
    }
}
