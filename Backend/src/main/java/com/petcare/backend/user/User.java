package com.petcare.backend.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String pwd;
    @Column
    private Integer phone;

    public User() {}

    public User(int id, String name, String email, String pwd, Integer phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.pwd = pwd;
        this.phone = phone;
    }

    public User(String name, String email, String pwd, Integer phone) {
        this.name = name;
        this.email = email;
        this.pwd = pwd;
        this.phone = phone;
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

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return this.pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Integer getPhone() {
        return this.phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            ", pwd='" + getPwd() + "'" +
            ", phone='" + getPhone() + "'" +
            "}";
    }
}
