package com.jdbc.model;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.jdbc.util.JDBCConnection;

public class Image {
    private int id;
    private Blob photo;
    private int aptID;
    static Connection con = JDBCConnection.getConnection();

    public Image() {
    }

    public Image(int id, Blob photo, int aptID) {
        this.id = id;
        this.photo = photo;
        this.aptID = aptID;
    }

    public Image(int id, Blob photo) {
        this.id = id;
        this.photo = photo;
    }

    public int getimageId() {
        return id;
    }

    public void setimageId(int id) {
        this.id = id;
    }

    public Blob getPhoto() {
        return photo;
    }

    public void setPhoto(Blob photo) {
        this.photo = photo;
    }

    public void setAptId(int aptID) {
        this.aptID = aptID;
    }

    public int getAptId() {
        return aptID;
    }

    public void addImage(Blob photo, int aid) throws SQLException {
        String query = "insert into image (image, aid) values (?, ?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setBlob(1, photo);
        ps.setInt(2, aid);
        ps.executeUpdate();
    }

    public void deleteImage(int id) throws SQLException {
        String query = "delete from image where id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
    }
}