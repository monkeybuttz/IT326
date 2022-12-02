package com.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jdbc.model.GroomingAppointment;
import com.jdbc.util.JDBCConnection;

public class GroomingAppointmentImp implements GroomingAppointmentDAO {
    static Connection con = JDBCConnection.getConnection();

    @Override
    public int add(GroomingAppointment apt) throws SQLException {
<<<<<<<< HEAD:backend/src/dao/GroomingAppointmentImp.java
        String query = "insert into groomingappointment(groomerID, petID, date, location, notes) VALUES (?, ?, ?, ?, ?)";
========
        String query = "INSERT into groomingappointment(groomerID, petID, date, location, notes) VALUES (?, ?, ?, ?, ?)";
>>>>>>>> 0410d5ab94c7959a29844bd7e44361227f1c1091:Backend/src/main/java/com/jdbc/dao/GroomingAppointmentImp.java
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, apt.getGroomerId());
        ps.setInt(2, apt.getPetId());
        ps.setString(3, apt.getDate());
        ps.setString(4, apt.getLocation());
        ps.setString(5, apt.getNotes());
        int n = ps.executeUpdate();
        return n;
    }
    
    @Override
    public void delete(int id) throws SQLException {
        String query = "delete from groomingappointment where aptID =?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    @Override
    public GroomingAppointment getGroomingAppointment(int id) throws SQLException {
        String query = "select * from groomingappointment where aptID =?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        GroomingAppointment gapt = new GroomingAppointment();
        ResultSet rs = ps.executeQuery();
        boolean c = false;
        while(rs.next()) {
            c = true;
            gapt.setAptId(rs.getInt("aptID"));
            gapt.setGroomerId(rs.getInt("groomerID"));
            gapt.setPetId(rs.getInt("petID"));
            gapt.setAptDate(rs.getString("date"));
            gapt.setLocation(rs.getString("location"));
            gapt.setNotes(rs.getString("notes"));
        }
        if(c) {
            return gapt;
        }
        else {
            return null;
        }
    }

    @Override
    public List<GroomingAppointment> getAppointments() throws SQLException {
        String query = "select * from groomingappointment";
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<GroomingAppointment> ls = new ArrayList<GroomingAppointment>();

        while(rs.next()) {
            GroomingAppointment gapt = new GroomingAppointment();
            gapt.setAptId(rs.getInt("aptID"));
            gapt.setGroomerId(rs.getInt("groomerID"));
            gapt.setPetId(rs.getInt("petID"));
            gapt.setAptDate(rs.getString("date"));
            gapt.setLocation(rs.getString("location"));
            gapt.setNotes(rs.getString("notes"));
            ls.add(gapt);
        }
        return ls;
    }
    
    @Override
    public void update(GroomingAppointment apt) throws SQLException {
        String query = "update groomingappointment set date = ?, location = ?, notes = ? where aptID = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, apt.getDate());
        ps.setString(2, apt.getLocation());
        ps.setString(3, apt.getNotes());
        ps.setInt(4, apt.getAptId());
        ps.executeUpdate();
    }
}