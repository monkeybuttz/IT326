import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import com.jdbc.dao.GroomingAppointmentImp;
import com.jdbc.dao.PetImp;
import com.jdbc.model.Groomer;
import com.jdbc.model.GroomingAppointment;
import com.jdbc.model.Owner;
import com.jdbc.model.Pet;

public class GroomingAptTests {

    @Test
    public void addApt() throws SQLException {
        Owner own = new Owner();
        own.setName("Braydon");
        own.setUsername("bhughe2");
        own.setPassword("password");
        own.setEmail("bhughe2@ilstu.edu");
        own.setPhoneNumber(1234567890);
        own.setIsGroomer(false);
        own.createAccount();
        Pet pet = new Pet();
        pet.setOwnerId(own.getOwnerID());
        PetImp pimp = new PetImp();
        pimp.add(pet);
        Groomer groomer = new Groomer();
        groomer.setName("Braydon");
        groomer.setUsername("bhughe2");
        groomer.setPassword("password");
        groomer.setEmail("bhughe2@ilstu.edu");
        groomer.setPhoneNumber(1234567890);
        groomer.setIsGroomer(true);
        groomer.createAccount();
        GroomingAppointment grooming = new GroomingAppointment();
        grooming.setNotes("This is an epic pet I think");
        grooming.setLocation("Guantanamo Bay");
        grooming.setAptDate("12/23/2022");
        grooming.setGroomerId(groomer.getOwnerID());
        grooming.setPetId(pet.getPetId());
        GroomingAppointmentImp imp = new GroomingAppointmentImp();
        assertEquals(0, imp.add(grooming));
    }
}