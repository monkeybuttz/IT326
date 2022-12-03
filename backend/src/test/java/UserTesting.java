import java.sql.SQLException;
import com.jdbc.model.Owner;
import com.jdbc.model.User;
import org.junit.jupiter.api.Test;

public class UserTesting {

    @Test
    public void testingCreateUserAccount() throws SQLException {
        User owner1 = new Owner();
        owner1.setEmail("gyonan@ilstu.edu");
        owner1.setOwnerID(5);
        owner1.setName("Greg Yonan");
        owner1.setUsername("gyonan");
        owner1.setPassword("Password123!");
        owner1.setPhoneNumber(8424747);
        owner1.setIsGroomer(false);
        owner1.createAccount();
    }

}