package model;

import java.sql.Blob;

public class Image {
    private int id;
    private Blob photo;
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