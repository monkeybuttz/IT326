package com.jdbc.model;

import java.sql.Blob;
import java.util.List;

public class GroomingAppointment {
    int aptId;
    String notes;
    String location;
    String date;
    int groomerId;
    int petId;
    boolean favorited;
    List<Blob> images;

    public GroomingAppointment() {}

    public GroomingAppointment(String notes, String location, String date, int petId, int groomerId) {
        this.notes = notes;
        this.location = location;
        this.date = date;
        this.petId = petId;
        this.groomerId = groomerId;
        favorited = false;
    }

    public GroomingAppointment(int aptId, String notes, String location, String date, int petId, int groomerId, List<Blob> images, boolean favorited) {
            this.aptId = aptId;
            this.notes = notes;
            this.location = location;
            this.groomerId = groomerId;
            this.images = images;
            this.date = date;
            this.favorited = favorited;
            this.petId = petId;
    }

    public int getAptId() {
        return this.aptId;
    }

    public void setAptId(int aptId) {
        this.aptId = aptId;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return this.date;
    }

    public void setAptDate(String date) {
        this.date = date;
    }

    public int getGroomerId() {
        return this.groomerId;
    }

    public void setGroomerId(int groomerId) {
        this.groomerId = groomerId;
    }

    public int getPetId() {
        return this.petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public boolean isFavorited() {
        return favorited;
    }

    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
    }

    public List<Blob> getImages() {
        return images;
    }

    public void setImages(List<Blob> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "{" +
            " notes='" + getNotes() + "'" +
            ", location='" + getLocation() + "'" +
            ", date='" + getDate() + "'" +
            ", groomerId='" + getGroomerId() + "'" +
            ", petId='" + getPetId() + "'" +
            "}";
    }
    

}
