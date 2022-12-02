
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.jdbc.model.Message;
import com.jdbc.model.Owner;

public class Testing {

    @Test
    public void testingCreateUserAccount() throws SQLException {
        Message words = new Message();
        List<Message> message = new ArrayList<Message>();
        message.add(words);
        Owner owner1 = new Owner(65, "Greg Yonan", "gyonan", "gyonan@ilstu.edu", "Password1234!", 84790, "enter this", message);
        // owner1.createAccount(owner1);
    }
}
