package com.jdbc.dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jdbc.model.GroomingAppointment;
import com.jdbc.util.JDBCConnection;

public class GroomingAppointmentImp {
    static Connection con = JDBCConnection.getConnection();

    public int add(GroomingAppointment apt) throws SQLException {
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
        for(int i = 0; i < blobs.size(); i++) {
            ps = con.prepareStatement(query);
            ps.setBlob(1, blobs.get(i));
            ps.setInt(2, id);
            ps.executeUpdate();
        }
        return id;
    }

    public void delete(int id) throws SQLException {
        String query = "delete from groomingappointment where aptID =?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
        query = "delete from image where aptID = ?";
        ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    public GroomingAppointment getGroomingAppointment(int id) throws SQLException {
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
            return gapt;
        } else {
            return null;
        }
    }

    public List<Blob> getAptImages(int id) throws SQLException {
        String query = "select * from image where aptID = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        boolean c = false;
        List<Blob> pics = new ArrayList<Blob>();
        while(rs.next()) {
            c = true;
            pics.add(rs.getBlob("image"));
        }
        if (c) {
            return pics;
        }
        else {
            return null;
        }
    }

    public void update(GroomingAppointment apt) throws SQLException {
        String query = "update groomingappointment set date = ?, location = ?, notes = ? where aptID = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, apt.getDate());
        ps.setString(2, apt.getLocation());
        ps.setString(3, apt.getNotes());
        ps.setInt(4, apt.getAptId());
        ps.executeUpdate();
    }

    public boolean favorite(int id) throws SQLException {
        String query = "update groomingappointment set favorited = true where aptID = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
        return true;
    }

    public boolean unfavorite(int id) throws SQLException {
        String query = "update groomingappointment set favorited = false where aptID = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
        return false;
    }
}
