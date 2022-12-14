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

import com.google.gson.Gson;
import com.jdbc.dao.PetImp;
import com.jdbc.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.jdbc.util.JDBCConnection;

@RestController
public class GroomingAppointmentController {
    static Connection con;
    static Gson gson;

    @Autowired
    public GroomingAppointmentController() {
        con = JDBCConnection.getConnection();
        gson = new Gson();
    }


    @GetMapping("/groomApt/{id}")
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
        gapt.setImages(getAptImages(gapt.getAptId()));
        if (c) {
            return new Gson().toJson(gapt);
        } else {
            return null;
        }
    }

    @PostMapping("/groomingapt/{id}")
    public String updateGroomingApt(@PathVariable int id, @RequestBody GroomingAppointment apt) throws SQLException {
        String query = "update groomingappointment set date = ?, location = ?, notes = ? where aptID = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, apt.getDate());
        ps.setString(2, apt.getLocation());
        ps.setString(3, apt.getNotes());
        ps.setInt(4, id);
        ps.executeUpdate();
        return new Gson().toJson("success");
    }
    
    @PostMapping("/groomingapt/add/{petID}")
    public String addGroomingApt(@RequestBody GroomingAppointment apt, @PathVariable int petID) throws SQLException {
        String query = "Insert into groomingappointment( groomerID, petID, date, location, notes, favorited) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, apt.getGroomerId());
        ps.setInt(2, petID);
        ps.setString(3, apt.getDate());
        ps.setString(4, apt.getLocation());
        ps.setString(5, apt.getNotes());
        ps.setBoolean(6, apt.isFavorited());
        ps.executeUpdate();
        int id = -1;
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            id = rs.getInt(1);
        }
        List<Blob> blobs = apt.getImages();
        query = "insert into image (image, aid) values (?, ?)";
        if (blobs != null) {
            for (int i = 0; i < blobs.size(); i++) {
                ps = con.prepareStatement(query);
                ps.setBlob(1, blobs.get(i));
                ps.setInt(2, id);
                ps.executeUpdate();
            }
        }

        return new Gson().toJson(id);
    }
    
    @GetMapping("/groomingapt/delete/{id}")
    public String deleteGroomingApt(@PathVariable int id) throws SQLException {
        String query = "delete from groomingappointment where aptID =?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
        query = "delete from image where aid = ?";
        ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
        return "success";
    }
    
    @PostMapping("/groomingapt/favorite/{id}")
    public void favoriteGroom(@PathVariable int id) throws SQLException {
        String query = "select petID from groomingappointment where aptID = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        int pid = -1;
        while (rs.next()) {
            pid = rs.getInt("petID");
        }
        query = "update groomingappointment set favorited = false where petID = ?";
        ps = con.prepareStatement(query);
        ps.setInt(1, pid);
        ps.executeUpdate();
        query = "update groomingappointment set favorited = true where aptID = ?";
        ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    public List<Blob> getAptImages(int id) throws SQLException {
        String query = "select * from image where aid = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        boolean c = false;
        List<Blob> pics = new ArrayList<Blob>();
        while (rs.next()) {
            c = true;
            pics.add(rs.getBlob("image"));
        }
        if (c) {
            return pics;
        } else {
            return null;
        }
    }

}
