import java.sql.SQLException;
import com.jdbc.model.Owner;
import org.junit.jupiter.api.Test;

public class PetImpTests {

    @Test
    public void insertPetTest() throws SQLException {
        Owner owner1 = new Owner();
        owner1.createAccount();
    }
}
