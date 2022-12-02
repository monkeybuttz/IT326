package com.jdbc.testing;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.jdbc.classes.Message;
import com.jdbc.classes.owner;

public class testing {

    @Test
    public void testingCases() {
        Message words = new Message("lets try this");
        List<Message> message = new ArrayList<Message>();
        message.add(words);
        owner owner1 = new owner(65, "Greg Yonan", "gyonan", "gyonan@ilstu.edu", "Password1234!", 84790, "enter this", message);
        //owner1.createAccount(owner1);
    }
}
