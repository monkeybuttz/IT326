package com.jdbc.model;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.jdbc.util.JDBCConnection;

public class Image {
    private int id;
    private Blob photo;
    static Connection con = JDBCConnection.getConnection();
    public Image() {}

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
}