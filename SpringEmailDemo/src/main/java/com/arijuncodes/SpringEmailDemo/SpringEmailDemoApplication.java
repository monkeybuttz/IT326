package com.arijuncodes.SpringEmailDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import java.util.Random;

@SpringBootApplication
public class SpringEmailDemoApplication {

	@Autowired
	private EmailSenderService senderService;

	public static void main(String[] args) {
		SpringApplication.run(SpringEmailDemoApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void sendMail()
	{
		int code = makeSixDigitNum();
		senderService.sendEmail("grantlane.glane@gmail.com", "Pet Care: Reset Password", "Verification Code to Reset Password: ", code);
	}

	private int makeSixDigitNum()
    {
		Random rand = new Random();
        int value = rand.nextInt(999999) + 1;
        while (value < 100000)
        {
            value *= 9;
        }
        return value;
    }

}
