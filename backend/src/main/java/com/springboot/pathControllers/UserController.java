package com.springboot.pathControllers;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties.Credential;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
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

    public static class LoginForm {
        private String login;
        private String password;

        LoginForm() {
        }

        LoginForm(String l, String p) {
            this.login = l;
            this.password = p;
        }

        public String getLogin() {
            return this.login;
        }
        
        public String getPassword() {
            return this.password;
        }
    }

    @Autowired
    public UserController() {
        con = JDBCConnection.getConnection();
    }

    @GetMapping("/user/{id}")
    public String getUser(@PathVariable int id) throws SQLException {
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
            groom.setPhoneNumber(rs.getLong("phoneNUM"));
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
        String query = "delete from user where userID =?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
        return true;
    }

   @PostMapping("/user/{id}")
   private String updateUser(@PathVariable int id, @RequestBody Owner newUserInfo) throws SQLException {
       String query = "update user set name = ?, username = ?, password = ?, email = ?, phoneNUM = ?, isGroomer = ? where userId = ? ";
       PreparedStatement ps = con.prepareStatement(query);
       ps.setString(1, newUserInfo.getName());
       ps.setString(2, newUserInfo.getUsername());
       ps.setString(3, newUserInfo.getPassword());
       ps.setString(4, newUserInfo.getEmail());
       ps.setLong(5, newUserInfo.getPhoneNumber());
       ps.setInt(6, newUserInfo.getIsGroomer());
       ps.setInt(7, id);
       ps.executeUpdate();
       return new Gson().toJson(true);
   }
    
   @PostMapping("/user")
   public String createUser(@RequestBody Owner user) throws SQLException {
       String query = "INSERT into user(name, username, password, email, phoneNUM, isGroomer) VALUES (?, ?, ?, ?, ?, ?)";
       PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
       ps.setString(1, user.getName());
       ps.setString(2, user.getUsername());
       ps.setString(3, user.getPassword());
       ps.setString(4, user.getEmail());
       ps.setLong(5, user.getPhoneNumber());
       ps.setInt(6, user.getIsGroomer());
       ps.executeUpdate();
       ResultSet rs = ps.getGeneratedKeys();
       if (rs.next()) {
           user.setID(rs.getInt(1));
       }
       return new Gson().toJson(user);
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
 
    @PostMapping("/user/login")
    public String login(@RequestBody LoginForm credentials) throws SQLException
    {
        String query = "select * from User where (username = ? OR email = ?) and password = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, credentials.getLogin());
        ps.setString(2, credentials.getLogin());
        ps.setString(3, credentials.getPassword());
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
