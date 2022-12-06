import java.sql.SQLException;
import javax.sql.rowset.serial.SerialBlob;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.jdbc.model.*;
import com.mysql.cj.jdbc.Blob;
import com.jdbc.dao.PetImp;
import com.springboot.pathControllers.*;
public class PetImpTests {

    // @Test
    // public void insertPetTest() throws SQLException {
    //     Blob blob = new SerialBlob(new byte[1024]);
    //     Pet pet = new Pet( 1, "Name", "Breed", "Notes", blob);

    //     PetImp petdao = new PetImp();

    //     assertNotEquals(-1, petdao.add(pet));

    // }

    // @Test
    // public void getPetTest() throws SQLException {
    //     Owner owner = new Owner();
    //     owner.setName("Braydon Hughes");
    //     owner.setEmail("bhughe2@ilstu.edu");
    //     owner.setUsername("bhughe2");
    //     owner.setPassword("1234");
    //     owner.setIsGroomer(0);
    //     owner.setPhoneNumber(1234567890);
    //     int oid = owner.createAccount();

    //     Blob blob = new SerialBlob(new byte[1024]);
    //     Pet pet = new Pet(oid, "Name", "Breed", "Notes", blob);
    //     PetImp petDAO = new PetImp();
    //     pet.setPetId(petDAO.add(pet));
    //     assertEquals(pet, petDAO.getPet(pet.getPetId()));

    // }

    @Test
    public void getPetContTest() throws SQLException {
        Owner owner = new Owner();
        owner.setName("Braydon Hughes");
        owner.setEmail("bhughe2@ilstu.edu");
        owner.setUsername("bhughe2");
        owner.setPassword("1234");
        owner.setIsGroomer(0);
        owner.setPhoneNumber(1234567890);
        int oid = owner.createAccount();
        Pet pet = new Pet(oid, "Name", "Breed", "Notes");
        PetController petCon = new PetController();
        PetImp pimp = new PetImp();
        int petId = pimp.add(pet);
        assertEquals(petCon.getPet(petId));

    }
    


    @Test
    public void updatePetTest() throws SQLException {
        Blob blob = new SerialBlob(new byte[1024]);
        Pet pet = new Pet( 1, "Name", "Breed", "Notes", blob);

        PetImp petDAO = new PetImp();
        pet.setPetId(petDAO.add(pet));

        pet.setBreed("newBreed");

        petDAO.update(pet);

        assertEquals(pet, petDAO.getPet(pet.getPetId()));
    }
    
    @Test
    public void deletePetTest() throws SQLException {
        Blob blob = new SerialBlob(new byte[1024]);
        Pet pet = new Pet( 1, "Name", "Breed", "Notes", blob);

        PetImp petDAO = new PetImp();
        pet.setPetId(petDAO.add(pet));

        petDAO.delete(pet.getPetId());

        assertNull(petDAO.getPet(1));
    }

    @Test
    public void getPetsTest() throws SQLException {
        Blob blob = new SerialBlob(new byte[1024]);
        Pet pet = new Pet( 1, "Name", "Breed", "Notes", blob);
        Pet pet2 = new Pet( 1, "Name", "Breed", "Notes", blob);
        Pet pet3 = new Pet(1, "Name", "Breed", "Notes", blob);

        PetImp petDAO = new PetImp();
        pet.setPetId(petDAO.add(pet));
        pet2.setPetId(petDAO.add(pet2));
        pet3.setPetId(petDAO.add(pet3));

        List<Pet> pets = Arrays.asList(pet, pet2, pet3);

        assertEquals(pets, petDAO.getPets(1));
    }

}
