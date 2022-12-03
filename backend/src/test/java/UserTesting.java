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
    public void testingCreateUserAccount() throws SQLException {
        Owner owner1 = new Owner();
        owner1.setEmail("gyonan@ilstu.edu");
        owner1.setOwnerID(5);
        owner1.setName("Greg Yonan");
        owner1.setUsername("gyonan");
        owner1.setPassword("Password123!");
        owner1.setPhoneNumber(8424747);
        owner1.setIsGroomer(false);
        owner1.createAccount();
        assertNotNull(owner1);
    }

    // createAccount() groomer (clear row in sql before each test)
    @Test
    public void testingCreateGroomerAccount() throws SQLException {
        Groomer groom = new Groomer();
        groom.setEmail("gyonan@ilstu.edu");
        groom.setOwnerID(5);
        groom.setName("Greg Yonan");
        groom.setUsername("gyonan");
        groom.setPassword("Password123!");
        groom.setPhoneNumber(8424747);
        groom.setIsGroomer(true);
        groom.createAccount();
        assertNotNull(groom);
    }

}
