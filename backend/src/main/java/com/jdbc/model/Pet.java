package com.jdbc.model;

import java.util.List;

import com.jdbc.model.GroomingAppointment;

public class Pet {
    int petID;
    int ownerID;
    String name;
    String breed;
    String notes;
    String image;
    List<GroomingAppointment> groomApts;
    List<String> documents;

    public Pet() {
    }

    public Pet(int petID, int ownerID, String name, String breed, String notes, String image, List<String> documents) {
        this.petID = petID;
        this.ownerID = ownerID;
        this.name = name;
        this.breed = breed;
        this.notes = notes;
        this.image = image;
        this.documents = documents;
    }

    public Pet(int petID, int ownerID, String name, String breed, String notes, String image) {
        this.petID = petID;
        this.ownerID = ownerID;
        this.name = name;
        this.breed = breed;
        this.notes = notes;
        this.image = image;
    }


    public Pet(int ownerID, String name, String breed, String notes, String image) {
        this.ownerID = ownerID;
        this.name = name;
        this.breed = breed;
        this.notes = notes;
        this.image = image;
    }

    public Pet(int ownerID, String name, String breed, String notes) {
        this.ownerID = ownerID;
        this.name = name;
        this.breed = breed;
        this.notes = notes;
    }

    public int getPetId() {
        return this.petID;
    }

    public void setPetId(int petID) {
        this.petID = petID;
    }

    public int getOwnerId() {
        return this.ownerID;
    }

    public void setOwnerId(int ownerID) {
        this.ownerID = ownerID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getBreed() {
        return this.breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<GroomingAppointment> getGroomingAppointments() {
        return this.groomApts;
    }

    public void setGroomingAppointments(List<GroomingAppointment> groomApts) {
        this.groomApts = groomApts;
    }

    public List<String> getDocuments() {
        return this.documents;
    }

    public void setDocuments(List<String> docs) {
        this.documents = docs;
    }

    @Override
    public String toString() {
        return "{" +
                " petID='" + getPetId() + "'" +
                ", ownerID='" + getOwnerId() + "'" +
                ", name='" + getName() + "'" +
                ", breed='" + getBreed() + "'" +
                ", notes='" + getNotes() + "'" +
                ", image='" + getImage() + "'" +
                "}";
    }

}
