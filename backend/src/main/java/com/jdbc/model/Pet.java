package com.jdbc.model;

import java.sql.Blob;

public class Pet {
    int petId;
    int ownerId;
    String name;
    String breed;
    String notes;
    Blob image;

    public Pet() {
    }

    public Pet(int petId, int ownerID, String name, String breed, String notes, Blob image) {
        this.petId = petId;
        this.ownerId = ownerID;
        this.name = name;
        this.breed = breed;
        this.notes = notes;
        this.image = image;
    }


    public int getPetId() {
        return this.petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public int getOwnerId() {
        return this.ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
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

    public Blob getImage() {
        return this.image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }


    @Override
    public String toString() {
        return "{" +
            " petId='" + getPetId() + "'" +
            ", ownerId='" + getOwnerId() + "'" +
            ", name='" + getName() + "'" +
            ", breed='" + getBreed() + "'" +
            ", notes='" + getNotes() + "'" +
            ", image='" + getImage() + "'" +
            "}";
    }


}
