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

import com.jdbc.dao.PetImp;
import com.jdbc.model.*;
import com.google.gson.Gson;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.jdbc.util.JDBCConnection;

@RestController
public class UserController {
    static Connection con;

    @Autowired
    public UserController() {
        con = JDBCConnection.getConnection();
    }

    @GetMapping("/user/{id}")
    private String getUser(@PathVariable int id) throws SQLException {
        String query = "select * from user where userID =?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        Groomer groom = new Groomer();
        ResultSet rs = ps.executeQuery();
        groom.setID(id);
        boolean c = false;
        while (rs.next()) {
            c = true;
            groom.setName(rs.getString("name"));
            groom.setUsername(rs.getString("username"));
            groom.setPassword(rs.getString("password"));
            groom.setEmail(rs.getString("email"));
            groom.setPhoneNumber(rs.getInt("phoneNUM"));
            groom.setIsGroomer(rs.getInt("isGroomer"));
        }
        if (c) {
            return new Gson().toJson(groom);
        } else {
            return null;
        }
    }

    @GetMapping("/user/delete/{id}")
    private boolean deleteUser(@PathVariable int id) throws SQLException {
        String query = """
            delete u, p, g, m, i
                from user u
                LEFT JOIN Pet p on u.userID = p.ownerID
                LEFT JOIN Groomingappointment g on p.petID = g.petID
                LEFT JOIN message m on  m.receiverID = u.userID or m.senderID = u.userID
                LEFT JOIN image i on  i.aid = g.aptID
                where u.userID = 1;
                 """;
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
        return true;
    }

    @PostMapping("/user/{id}")
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
            groom.setIsGroomer(rs.getInt("isGroomer"));
        }
        if (c) {
            return new Gson().toJson(groom);
        } else {
            return null;
        }
    }

    @GetMapping("/searchForGroomer/{name}")
    public String searchForGroomer(@PathVariable String name) throws SQLException {
        name = "%" + name + "%";
        List<Groomer> ls = new ArrayList<Groomer>();
        String query = "select * from User where (name LIKE ? OR username LIKE ?) AND isGroomer = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, name);
        ps.setString(2, name);
        ps.setBoolean(3, true);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Groomer groomer = new Groomer();
            groomer.setID(rs.getInt("userID"));
            groomer.setName(rs.getString("name"));
            groomer.setUsername(rs.getString("username"));
            groomer.setPassword(rs.getString("password"));
            groomer.setEmail(rs.getString("email"));
            groomer.setPhoneNumber(rs.getInt("phoneNUM"));
            groomer.setIsGroomer(rs.getInt("isGroomer"));
            ls.add(groomer);
        }
        return new Gson().toJson(ls);
    }
 
	
    public String login(String login, String password) throws SQLException
    {
        String query = "select * from User where (username = ? OR email = ?) and password = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, login);
        ps.setString(2, login);
        ps.setString(3, password);
        ResultSet rs = ps.executeQuery();
        User user = null;
        while (rs.next())
        {
            if (rs.getBoolean("isGroomer"))
            {
                user = new Groomer();
            }
            else
            {
                user = new Owner();
            }

            user.setID(rs.getInt("userID"));
            user.setName(rs.getString("name"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setPhoneNumber(rs.getInt("phoneNUM"));
        }

        return new Gson().toJson(user);
    }
	
}
