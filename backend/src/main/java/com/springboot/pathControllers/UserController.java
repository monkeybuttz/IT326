package com.springboot.pathControllers;

import java.sql.SQLException;
import java.util.List;

import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import com.jdbc.dao.PetImp;
import com.jdbc.model.*;
import com.google.gson.Gson;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.jdbc.model.GroomingAppointment;
import com.jdbc.util.JDBCConnection;

@RestController
public class UserController {
    static Connection con;

    @Autowired
    public UserController() {
        con = JDBCConnection.getConnection();
    }

    @GetMapping("user/{id}")
    private String getUser(@PathVariable int id) throws SQLException {
        String query = "select * from user where userID =?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        Groomer groom = new Groomer();
        ResultSet rs = ps.executeQuery();
        boolean c = false;
        while (rs.next()) {
            c = true;
            groom.setName(rs.getString("name"));
            groom.setUsername(rs.getString("username"));
            groom.setPassword(rs.getString("password"));
            groom.setEmail(rs.getString("email"));
            groom.setPhoneNumber(rs.getInt("phoneNUM"));
            groom.setIsGroomer(rs.getBoolean("isGroomer"));
        }
        if (c) {
            return new Gson().toJson(groom);
        } else {
            return null;
        }
    }

    @PostMapping("user/{id}")
    private String updateUser(@PathVariable int id) throws SQLException {
        String query = "select * from user where userID =?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        Groomer groom = new Groomer();
        ResultSet rs = ps.executeQuery();
        boolean c = false;
        while (rs.next()) {
            c = true;
            groom.setName(rs.getString("name"));
            groom.setUsername(rs.getString("username"));
            groom.setPassword(rs.getString("password"));
            groom.setEmail(rs.getString("email"));
            groom.setPhoneNumber(rs.getInt("phoneNUM"));
            groom.setIsGroomer(rs.getBoolean("isGroomer"));
        }
        if (c) {
            return new Gson().toJson(groom);
        } else {
            return null;
        }
    }
	
}
