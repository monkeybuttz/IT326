import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import org.junit.jupiter.api.Test;

import com.jdbc.dao.GroomingAppointmentImp;
import com.jdbc.dao.PetImp;
import com.jdbc.model.Groomer;
import com.jdbc.model.GroomingAppointment;
import com.jdbc.model.Image;
import com.jdbc.model.Owner;
import com.jdbc.model.Pet;

public class GroomingAptTests {
    
    @Test
    public void addApt() throws SQLException {
        Owner owner = new Owner();
        owner.setName("Braydon Hughes");
        owner.setEmail("bhughe2@ilstu.edu");
        owner.setUsername("bhughe2");
        owner.setPassword("1234");
        owner.setIsGroomer(0);
        owner.setPhoneNumber(1234567890);
        int oid = owner.createAccount();

        Groomer groomer = new Groomer();
        groomer.setName("Traydon Tughes");
        groomer.setEmail("thughe32@ilstu.edu");
        groomer.setUsername("thughe3");
        groomer.setPassword("2345");
        groomer.setIsGroomer(1);
        groomer.setPhoneNumber(1234567890);
        int gid = groomer.createAccount();

        Blob blob = new SerialBlob(new byte[1024]);
        Pet pet = new Pet(oid, "Fido", "Dog", "", blob);
        PetImp pimp = new PetImp();
        int pid = pimp.add(pet);
        
        GroomingAppointment grooming = new GroomingAppointment();
        grooming.setNotes("This is an epic pet I think");
        grooming.setLocation("Guantanamo Bay");
        grooming.setAptDate("12/23/2022");
        grooming.setGroomerId(gid);
        grooming.setPetId(pid);
        Blob blob1 = new SerialBlob(new byte[1024]);
        Blob blob2 = new SerialBlob(new byte[1024]);
        List<Image> blobs = Arrays.asList(new Image(1, blob1), new Image(2, blob2));
        grooming.setImages(blobs);
        GroomingAppointmentImp imp = new GroomingAppointmentImp();
        assertNotEquals(-1, imp.add(grooming));
    }

    @Test
    public void deleteApt() throws SQLException {
        Owner owner = new Owner();
        owner.setName("Braydon Hughes");
        owner.setEmail("bhughe2@ilstu.edu");
        owner.setUsername("bhughe2");
        owner.setPassword("1234");
        owner.setIsGroomer(false);
        owner.setPhoneNumber(1234567890);
        int oid = owner.createAccount();

        Groomer groomer = new Groomer();
        groomer.setName("Traydon Tughes");
        groomer.setEmail("thughe32@ilstu.edu");
        groomer.setUsername("thughe3");
        groomer.setPassword("2345");
        groomer.setIsGroomer(true);
        groomer.setPhoneNumber(1234567890);
        int gid = groomer.createAccount();

        Blob blob = new SerialBlob(new byte[1024]);
        Pet pet = new Pet(oid, "Fido", "Dog", "", blob);
        PetImp pimp = new PetImp();
        int pid = pimp.add(pet);
        
        GroomingAppointment grooming = new GroomingAppointment();
        grooming.setNotes("This is a cool pet I think");
        grooming.setLocation("Guantanamo Bay");
        grooming.setAptDate("12/23/2022");
        grooming.setGroomerId(gid);
        grooming.setPetId(pid);
        GroomingAppointmentImp imp = new GroomingAppointmentImp();
        int aid = imp.add(grooming);
        imp.delete(aid);
        assertNull(imp.getGroomingAppointment(aid));
    }

    @Test
    public void getAptTest() throws SQLException {
        Owner owner = new Owner();
        owner.setName("Braydon Hughes");
        owner.setEmail("bhughe2@ilstu.edu");
        owner.setUsername("bhughe2");
        owner.setPassword("1234");
        owner.setIsGroomer(0);
        owner.setPhoneNumber(1234567890);
        int oid = owner.createAccount();

        Groomer groomer = new Groomer();
        groomer.setName("Traydon Tughes");
        groomer.setEmail("thughe32@ilstu.edu");
        groomer.setUsername("thughe3");
        groomer.setPassword("2345");
        groomer.setIsGroomer(1);
        groomer.setPhoneNumber(1234567890);
        int gid = groomer.createAccount();

        PetImp pimp = new PetImp();
        Blob blob = new SerialBlob(new byte[1024]);
        Pet pet = new Pet(oid, "Fido", "Dog", "", blob);
        int pid = pimp.add(pet);
        
        GroomingAppointment grooming = new GroomingAppointment();
        GroomingAppointmentImp imp = new GroomingAppointmentImp();
        grooming.setNotes("This is a cool pet I think");
        grooming.setLocation("Guantanamo Bay");
        grooming.setAptDate("12/23/2022");
        grooming.setGroomerId(gid);
        grooming.setPetId(pid);
        grooming.setAptId(imp.add(grooming));
        
        assertEquals(grooming, imp.getGroomingAppointment(grooming.getAptId()));
    }

    @Test
    public void favoriteTest() throws SQLException {
        Owner owner = new Owner();
        owner.setName("Braydon Hughes");
        owner.setEmail("bhughe2@ilstu.edu");
        owner.setUsername("bhughe2");
        owner.setPassword("1234");
        owner.setIsGroomer(false);
        owner.setPhoneNumber(1234567890);
        int oid = owner.createAccount();

        Groomer groomer = new Groomer();
        groomer.setName("Traydon Tughes");
        groomer.setEmail("thughe32@ilstu.edu");
        groomer.setUsername("thughe3");
        groomer.setPassword("2345");
        groomer.setIsGroomer(true);
        groomer.setPhoneNumber(1234567890);
        int gid = groomer.createAccount();

        PetImp pimp = new PetImp();
        Blob blob = new SerialBlob(new byte[1024]);
        Pet pet = new Pet(oid, "Fido", "Dog", "", blob);
        int pid = pimp.add(pet);
        
        GroomingAppointment grooming = new GroomingAppointment();
        GroomingAppointmentImp imp = new GroomingAppointmentImp();
        grooming.setNotes("This is a cool pet I think");
        grooming.setLocation("Guantanamo Bay");
        grooming.setAptDate("12/23/2022");
        grooming.setGroomerId(gid);
        grooming.setPetId(pid);
        int aid = imp.add(grooming);

        assertEquals(true, imp.favorite(aid));
    }

    @Test
    public void unfavoriteTest() throws SQLException {
        Owner owner = new Owner();
        owner.setName("Braydon Hughes");
        owner.setEmail("bhughe2@ilstu.edu");
        owner.setUsername("bhughe2");
        owner.setPassword("1234");
        owner.setIsGroomer(false);
        owner.setPhoneNumber(1234567890);
        int oid = owner.createAccount();

        Groomer groomer = new Groomer();
        groomer.setName("Traydon Tughes");
        groomer.setEmail("thughe32@ilstu.edu");
        groomer.setUsername("thughe3");
        groomer.setPassword("2345");
        groomer.setIsGroomer(true);
        groomer.setPhoneNumber(1234567890);
        int gid = groomer.createAccount();

        PetImp pimp = new PetImp();
        Blob blob = new SerialBlob(new byte[1024]);
        Pet pet = new Pet(oid, "Fido", "Dog", "", blob);
        int pid = pimp.add(pet);
        
        GroomingAppointment grooming = new GroomingAppointment();
        GroomingAppointmentImp imp = new GroomingAppointmentImp();
        grooming.setNotes("This is a cool pet I think");
        grooming.setLocation("Guantanamo Bay");
        grooming.setAptDate("12/23/2022");
        grooming.setGroomerId(gid);
        grooming.setPetId(pid);
        int aid = imp.add(grooming);

        assertEquals(false, imp.unfavorite(aid));
    }
}