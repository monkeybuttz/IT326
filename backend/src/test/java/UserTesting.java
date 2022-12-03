import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.SQLException;

import com.jdbc.model.Groomer;
import com.jdbc.model.Owner;
import org.junit.jupiter.api.Test;

public class UserTesting {
    /*
     * Greg Yonan
     */

    // createAccount() owner (clear row in sql before each test)
    @Test
    public void testingCreateOwnerAccount() throws SQLException {
        Owner owner1 = new Owner();
        owner1.setEmail("gyonan@ilstu.edu");
        owner1.setID(5);
        owner1.setName("Greg Yonan");
        owner1.setUsername("gyonan");
        owner1.setPassword("Password123!");
        owner1.setPhoneNumber(8424747);
        owner1.setIsGroomer(false);
        int test = owner1.createAccount();
        assertEquals(0, test);
    }

    // createAccount() groomer (clear row in sql before each test)
    @Test
    public void testingCreateGroomerAccount() throws SQLException {
        Groomer groom = new Groomer();
        groom.setEmail("gyonan@ilstu.edu");
        groom.setID(5);
        groom.setName("Greg Yonan");
        groom.setUsername("gyonan");
        groom.setPassword("Password123!");
        groom.setPhoneNumber(8424747);
        groom.setIsGroomer(true);
        int test = groom.createAccount();
        assertEquals(0, test);
    }

    // readAccount() owner (userID must be in table)
    @Test
    public void testingReadOwnerAccount() throws SQLException {
        Owner owner1 = new Owner();
        owner1.setID(5);
        Owner owner2 = owner1.readAccount();
        assertNotNull(owner2);
    }

    // readAccount() groomer (userID must be in table)
    @Test
    public void testingReadGroomerAccount() throws SQLException {
        Groomer groom = new Groomer();
        groom.setID(5);
        Groomer groom2 = groom.readAccount();
        assertNotNull(groom2);
    }

    // updateAccount() owner (make sure ownerID matches in sql before each test,
    // refresh table afterwards)
    @Test
    public void testingUpdateOwnerAccount() throws SQLException {
        Owner owner1 = new Owner();
        owner1.setEmail("gyonan@ilstu.edu");
        owner1.setID(5);
        owner1.setName("Greg Yonan");
        owner1.setUsername("gyonan");
        owner1.setPassword("Password123!");
        owner1.setPhoneNumber(8424747);
        owner1.setIsGroomer(false);
        Owner owner2 = new Owner();
        owner2.setEmail("jackferg@ilstu.edu");
        owner2.setID(5);
        owner2.setName("Jack Ferg");
        owner2.setUsername("jFerg");
        owner2.setPassword("Password1234!");
        owner2.setPhoneNumber(84254747);
        owner2.setIsGroomer(false);
        boolean flag = owner1.updateAccount(owner2);
        assertEquals(true, flag);
    }

    // updateAccount() groomer (make sure ownerID matches in sql before each test,
    // refresh table afterwards)
    @Test
    public void testingUpdateGroomerAccount() throws SQLException {
        Groomer groom1 = new Groomer();
        groom1.setEmail("gyonan@ilstu.edu");
        groom1.setID(5);
        groom1.setName("Greg Yonan");
        groom1.setUsername("gyonan");
        groom1.setPassword("Password123!");
        groom1.setPhoneNumber(8424747);
        groom1.setIsGroomer(false);
        Groomer groom2 = new Groomer();
        groom2.setEmail("jackferg@ilstu.edu");
        groom2.setID(5);
        groom2.setName("Jack Ferg");
        groom2.setUsername("jFerg");
        groom2.setPassword("Password1234!");
        groom2.setPhoneNumber(84254747);
        groom2.setIsGroomer(true);
        boolean flag = groom1.updateAccount(groom2);
        assertEquals(true, flag);
    }

    // deleteAccount() owner (make sure ownerID matches in sql before each test,
    // refresh table afterwards)
    @Test
    public void testingDeleteOwnerAccounnt() throws SQLException {
        Owner owner1 = new Owner();
        owner1.setID(5);
        boolean flag = owner1.deleteAccount();
        assertEquals(true, flag);
    }

    // deleteAccount() groomer (make sure ownerID matches in sql before each test,
    // refresh table afterwards)
    @Test
    public void testingDeleteGroomerAccounnt() throws SQLException {
        Groomer groom1 = new Groomer();
        groom1.setID(5);
        boolean flag = groom1.deleteAccount();
        assertEquals(true, flag);
    }

}
