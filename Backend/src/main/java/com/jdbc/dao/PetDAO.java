package com.jdbc.dao;

import java.sql.SQLException;
import java.util.List;

import com.jdbc.model.Pet;
import com.jdbc.model.GroomingAppointment;

public interface PetDAO {

    public Pet getPet(int id) throws SQLException;

    public int add(Pet pet) throws SQLException;

    public void delete(int id) throws SQLException;

    public List<Pet> getPets() throws SQLException;
    
    public void update(Pet pet) throws SQLException;

}
