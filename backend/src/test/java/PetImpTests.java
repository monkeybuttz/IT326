import java.sql.SQLException;
import com.jdbc.model.Owner;
import org.junit.jupiter.api.Test;

public class PetImpTests {

    @Test
    public void insertPetTest() throws SQLException {
        Owner owner1 = new Owner(65, "Greg Yonan", "gyonan", "gyonan@ilstu.edu", "Password1234!", 84790, "enter this",
                false);
        owner1.createAccount();
    }
}
