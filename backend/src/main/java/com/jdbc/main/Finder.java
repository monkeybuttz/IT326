package com.jdbc.main;

import java.sql.*;

import com.jdbc.util.JDBCConnection;
import com.jdbc.model.Groomer;
import com.jdbc.model.Pet;
import com.jdbc.model.User;

public class Finder 
{
    private User user;
    private Connection con = JDBCConnection.getConnection();
    public Finder(User user)
    {
        this.user = user;
    }


    // public Groomer searchForGroomer(String name) throws SQLException
    // {
    //     Groomer groomer = new Groomer(0, name, name, name, 0, name, null);
    //     boolean check = false;
    //     if (!user.getIsGroomer())
    //     {
    //         String query = "select * from User where (name LIKE = %?% OR username LIKE = %?%) AND isGroomer = ?";
    //         PreparedStatement ps = con.prepareStatement(query);
    //         ps.setString(1, name);
    //         ps.setString(2, name);
    //         ps.setBoolean(3, true);
    //         ResultSet rs = ps.executeQuery();
    //         while (rs.next())
    //         {
    //             check = true;
    //             groomer.setOwnerId(rs.getInt("userID"));
    //             groomer.setName(rs.getString("name"));
    //             groomer.setUserName(rs.getString("username"));
    //             groomer.setPassword(rs.getString("password"));
    //             groomer.setEmail(rs.getString("email"));
    //             groomer.setPhoneNum(rs.getInt("phoneNUM"));
    //             //Unsure if this is neccessary
    //             //groomer.setIsGroomer(true);
    //         }

    //         return groomer;
    //     }
    //     else
    //     {
    //         return null;
    //     }
    // }

    public Pet searchForPet(String name) throws SQLException
    {
        Pet dog = new Pet();
        String query = "select * from Pet where (name LIKE = %?%)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        boolean check = false;
        while (rs.next())
        {
            check = true;
            dog.setPetId(rs.getInt("petID"));
            dog.setOwnerId(rs.getInt("ownerID"));
            dog.setName(rs.getString("name"));
            dog.setBreed(rs.getString("breed"));
            dog.setNotes(rs.getString("notes"));
            dog.setImage(rs.getBlob("image"));
        }
        if (check)
        {
            return dog;
        }
        else 
        {
            return null;
        }
    }
}
