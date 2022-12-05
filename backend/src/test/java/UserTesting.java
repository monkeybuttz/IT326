import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.SQLException;

import com.jdbc.model.Groomer;
import com.jdbc.model.Owner;

import org.junit.jupiter.api.Test;

public class UserTesting {
    /*
     * Greg Yonan
     * Testing for User, Owner, and Groomer classes
     */

    // createAccount() owner (clear row in sql before each test)
    @Test
    public void testingCreateOwnerAccount() throws SQLException {
        Owner owner1 = new Owner();
        owner1.setEmail("jfergu2@ilstu.edu");
        owner1.setName("Jack Ferguson");
        owner1.setUsername("jfergu2");
        owner1.setPassword("Password123!");
        owner1.setPhoneNumber(2706280);
        owner1.setIsGroomer(0);
        int test = owner1.createAccount();
        assertNotEquals(0, test);
    }

    // createAccount() groomer (clear row in sql before each test)
    @Test
    public void testingCreateGroomerAccount() throws SQLException {
        Groomer groom = new Groomer();
        groom.setEmail("gyan@ilstu.edu");
        groom.setName("Greg Ynan");
        groom.setUsername("jhvhg");
        groom.setPassword("23!");
        groom.setPhoneNumber(8424);
        groom.setIsGroomer(1);
        int test = groom.createAccount();
        assertNotEquals(0, test);
    }

    // readAccount() owner (userID must be in table)
    @Test
    public void testingReadOwnerAccount() throws SQLException {
        Owner owner1 = new Owner();
        owner1.setID(7);
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
        owner1.setEmail("gy@ilstu.edu");
        owner1.setID(5);
        owner1.setName("Greg Yonan");
        owner1.setUsername("gyonan");
        owner1.setPassword("Password123!");
        owner1.setPhoneNumber(8424747);
        owner1.setIsGroomer(0);
        Owner owner2 = new Owner();
        owner2.setEmail("jackferg@ilstu.edu");
        owner2.setID(5);
        owner2.setName("Jack Ferg");
        owner2.setUsername("jFerg");
        owner2.setPassword("Password1234!");
        owner2.setPhoneNumber(84254747);
        owner2.setIsGroomer(0);
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
        groom1.setIsGroomer(1);
        Groomer groom2 = new Groomer();
        groom2.setEmail("jackferg@ilstu.edu");
        groom2.setID(5);
        groom2.setName("Jack Ferg");
        groom2.setUsername("jFerg");
        groom2.setPassword("Password1234!");
        groom2.setPhoneNumber(84254747);
        groom2.setIsGroomer(1);
        boolean flag = groom1.updateAccount(groom2);
        assertEquals(true, flag);
    }

    // deleteAccount() owner (make sure ownerID matches in sql before each test,
    // refresh table afterwards)
    @Test
    public void testingDeleteOwnerAccounnt() throws SQLException {
        Owner owner1 = new Owner();
        owner1.setID(16);
        boolean flag = owner1.deleteAccount();
        assertEquals(true, flag);
    }

    // deleteAccount() groomer (make sure ownerID matches in sql before each test,
    // refresh table afterwards)
    @Test
    public void testingDeleteGroomerAccounnt() throws SQLException {
        Groomer groom1 = new Groomer();
        groom1.setID(15);
        boolean flag = groom1.deleteAccount();
        assertEquals(true, flag);
    }

}
