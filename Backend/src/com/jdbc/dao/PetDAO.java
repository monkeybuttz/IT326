package Backend.src.com.jdbc.dao;

import java.sql.SQLException;
import java.util.List;

import Backend.src.com.jdbc.model.Pet;

public interface PetDAO {

    
    public int add(PET pet) throws SQLException;

    public void delete(int id) throws SQLException;

    public Pet getGroomingAppointment(int id) throws SQLException;

    public List<Pet> getPets() throws SQLException;
    
    public void update(Pet pet) throws SQLException;

}
