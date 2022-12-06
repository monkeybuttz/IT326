import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;

import com.jdbc.model.*;

/*
 * Tsts the PetTest.java Program
 * Name: Grant Lane
 */
public class PetModelTests {
    @Test
    // Testing creating an empty Pet
    public void emptyPetTest() {
        Pet emp = new Pet();
        assertNotNull(emp);
        ;
    }

    @Test
    // Testing creating a Pet with all parameters
    public void createPetTest() throws SQLException {
        String blob = "";
        Pet pet = new Pet(1,  2, "Name", "Breed", "Notes", blob);
        assertNotNull(pet);
    }

    @Test
    // Test get PetId
    public void getPetIdTest() throws SQLException {
        String blob = "";
        Pet pet = new Pet(1,  2, "Name", "Breed", "Notes",  blob);
        int id = pet.getPetId();
        assertEquals(1, id);
    }

    @Test
    // Test get Pet Notes
    public void getNotesTest() throws SQLException {
        String blob = "";
        Pet pet = new Pet(1,  2, "Name", "Breed", "Notes",  blob);
        String Notes = pet.getNotes();
        assertEquals("Howdy!", Notes);
    }

    @Test
    // Test get PetId
    public void getNameTest() throws SQLException {
        String blob = "";
        Pet pet = new Pet(1,  2, "Name", "Breed", "Notes",  blob);
        String name = pet.getName();
        assertEquals(2, name);
    }

    @Test
    // Test get PetId
    public void getBreedTest() throws SQLException {
        String blob = "";
        Pet pet = new Pet(1, 2, "Name", "Breed", "Notes", blob);
        String breed = pet.getBreed();
        assertEquals(2, breed);
    }

    @Test
    // Test get PetId
    public void getOwnerIdTest() throws SQLException {
        String blob = "";
        Pet pet = new Pet(1, 2, "Name", "Breed", "Notes", blob);
        int id = pet.getOwnerId();
        assertEquals(3, id);
    }
    
    @Test
    // Test get PetId
    public void getImageTest() throws SQLException {
        String blob = "";
        Pet pet = new Pet(1, 2, "Name", "Breed", "Notes", blob);
        String image = pet.getImage();
        assertEquals(blob, image);
    }

    @Test
    // Test get PetId
    public void setPetIdTest() {
        int id = 1;

        Pet pet = new Pet();
        pet.setPetId(id);

        assertEquals(id, pet.getPetId());
    }

    @Test
    // Test get PetId
    public void setNotesTest() {
        String Notes = "Hi! How are ya?";

        Pet pet = new Pet();
        pet.setNotes(Notes);

        assertEquals(Notes, pet.getNotes());
    }

    @Test
    // Test get PetId
    public void setNameTest() {
        String name = "name";

        Pet pet = new Pet();
        pet.setName(name);

        assertEquals(name, pet.getName());
    }

    @Test
    // Test set PetId
    public void setBreedTest() {
        String breed = "Breed";

        Pet pet = new Pet();
        pet.setBreed(breed);

        assertEquals(breed, pet.getBreed());
    }

    @Test
    // Test get PetId
    public void setOwnerTest() {
        int id = 1;

        Pet pet = new Pet();
        pet.setOwnerId(id);

        assertEquals(id, pet.getOwnerId());
    }

    @Test
    // Test get PetId
    public void setImageTest() throws SQLException {
        String blob = "";

        Pet pet = new Pet();
        pet.setImage(blob);

        assertEquals(blob, pet.getImage());
    }

}
