package com.jdbc.model;

import java.sql.Blob;

public class GroomingAppointment {
    int aptId;
    String notes;
    String location;
    String date;
    int groomerId;
    int petId;
    boolean favorited;

    public GroomingAppointment() {}


    public GroomingAppointment(int aptId, String notes, String location, String date, Pet pet, Groomer groomer) {
        this.aptId = aptId;
        this.notes = notes;
        this.location = location;
        this.date = date;
        this.petId = pet.getPetId();
        this.groomerId = groomer.getGroomerId();
        favorited = false;
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
