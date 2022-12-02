
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.jdbc.model.Owner;

public class UserTesting {

    @Test
    public void testingCreateUserAccount() throws SQLException {
        Owner owner1 = new Owner(65, "Greg Yonan", "gyonan", "gyonan@ilstu.edu", "Password1234!", 84790, "enter this", false);
        // owner1.createAccount(owner1);
    }
}
