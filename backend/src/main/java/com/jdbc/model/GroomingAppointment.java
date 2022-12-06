package com.jdbc.model;

import java.sql.Blob;
import java.util.List;

public class GroomingAppointment {
    int aptID;
    String notes;
    String location;
    String date;
    int groomerId;
    int petID;
    boolean favorited;
    List<Blob> images;

    public GroomingAppointment() {
    }

    public GroomingAppointment(String notes, String location, String date, int petID, int groomerId) {
        this.notes = notes;
        this.location = location;
        this.date = date;
        this.petID = petID;
        this.groomerId = groomerId;
        favorited = false;
    }

    public GroomingAppointment(int aptID, String notes, String location, String date, int petID, int groomerId,
            List<Blob> images, boolean favorited) {
        this.aptID = aptID;
        this.notes = notes;
        this.location = location;
        this.groomerId = groomerId;
        this.images = images;
        this.date = date;
        this.favorited = favorited;
        this.petID = petID;
    }

    public int getAptId() {
        return this.aptID;
    }

    public void setAptId(int aptID) {
        this.aptID = aptID;
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
        return this.petID;
    }

    public void setPetId(int petID) {
        this.petID = petID;
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
                ", petID='" + getPetId() + "'" +
                "}";
    }

}
