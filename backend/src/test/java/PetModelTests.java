import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Blob;
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
        Blob blob = new SerialBlob(new byte[1024]);
        Pet Pet = new Pet(1,  2, "Name", "Breed", "Notes", blob);
        assertNotNull(Pet);
    }

    @Test
    // Test get PetId
    public void getPetIdTest() throws SQLException {
        Blob blob = new SerialBlob(new byte[1024]);
        Pet Pet = new Pet(1,  2, "Name", "Breed", "Notes",  blob);
        int id = Pet.getPetId();
        assertEquals(1, id);
    }

    @Test
    // Test get Pet Notes
    public void getNotesTest() throws SQLException {
        Blob blob = new SerialBlob(new byte[1024]);
        Pet Pet = new Pet(1,  2, "Name", "Breed", "Notes",  blob);
        String Notes = Pet.getNotes();
        assertEquals("Howdy!", Notes);
    }

    @Test
    // Test get PetId
    public void getNameTest() throws SQLException {
        Blob blob = new SerialBlob(new byte[1024]);
        Pet Pet = new Pet(1,  2, "Name", "Breed", "Notes",  blob);
        String name = Pet.getName();
        assertEquals(2, name);
    }

    @Test
    // Test get PetId
    public void getBreedTest() throws SQLException {
        Blob blob = new SerialBlob(new byte[1024]);
        Pet Pet = new Pet(1, 2, "Name", "Breed", "Notes", blob);
        String breed = Pet.getBreed();
        assertEquals(2, breed);
    }

    @Test
    // Test get PetId
    public void getOwnerIdTest() throws SQLException {
        Blob blob = new SerialBlob(new byte[1024]);
        Pet Pet = new Pet(1, 2, "Name", "Breed", "Notes", blob);
        int id = Pet.getOwnerId();
        assertEquals(3, id);
    }
    
    @Test
    // Test get PetId
    public void getImageTest() throws SQLException {
        Blob blob = new SerialBlob(new byte[1024]);
        Pet Pet = new Pet(1, 2, "Name", "Breed", "Notes", blob);
        Blob image = Pet.getImage();
        assertEquals(blob, image);
    }

    @Test
    // Test get PetId
    public void setPetIdTest() {
        int id = 1;

        Pet Pet = new Pet();
        Pet.setPetId(id);

        assertEquals(id, Pet.getPetId());
    }

    @Test
    // Test get PetId
    public void setNotesTest() {
        String Notes = "Hi! How are ya?";

        Pet Pet = new Pet();
        Pet.setNotes(Notes);

        assertEquals(Notes, Pet.getNotes());
    }

    @Test
    // Test get PetId
    public void setNameTest() {
        String name = "name";

        Pet Pet = new Pet();
        Pet.setName(name);

        assertEquals(name, Pet.getName());
    }

    @Test
    // Test set PetId
    public void setBreedTest() {
        String breed = "Breed";

        Pet Pet = new Pet();
        Pet.setBreed(breed);

        assertEquals(breed, Pet.getBreed());
    }

    @Test
    // Test get PetId
    public void setOwnerTest() {
        int id = 1;

        Pet Pet = new Pet();
        Pet.setOwnerId(id);

        assertEquals(id, Pet.getOwnerId());
    }

    @Test
    // Test get PetId
    public void setImageTest() throws SQLException {
        Blob blob = new SerialBlob(new byte[1024]);

        Pet Pet = new Pet();
        Pet.setImage(blob);

        assertEquals(blob, Pet.getImage());
    }

}
