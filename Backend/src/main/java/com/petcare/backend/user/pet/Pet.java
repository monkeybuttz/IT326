package com.petcare.backend.pet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private String name;
    @Column
    private String breed;
    @Column
    private String notes;
    @Column
    private Integer[] photos;

    public Pet() {
    }

    public Pet(int id, String name, String breed, String notes, Integer[] photos) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.notes = notes;
        this.photos = photos;
    }

    public Pet(String name, String breed, String notes, Integer[] photos) {
        this.name = name;
        this.breed = breed;
        this.notes = notes;
        this.photos = photos;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getbreed() {
        return this.breed;
    }

    public void setbreed(String breed) {
        this.breed = breed;
    }

    public String getnotes() {
        return this.notes;
    }

    public void setnotes(String notes) {
        this.notes = notes;
    }

    public Integer[] getphotos() {
        return this.photos;
    }

    public void setphotos(Integer[] photos) {
        this.photos = photos;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", name='" + getName() + "'" +
                ", breed='" + getbreed() + "'" +
                ", notes='" + getnotes() + "'" +
                ", photos='" + getphotos() + "'" +
                "}";
    }
    
    public String toJson() {
        return "\"Pet\" : {" +
                " \"id\":" + getId() + "'" +
                ", \"name\":\"" + getName() + "\"" +
                ", \"breed\":\"" + getbreed() + "\"" +
                ", \"notes\":\"" + getnotes() + "\":" +
                ", \"photos\":\"" + getphotos() + "\":" +
                "}";
    }
}
