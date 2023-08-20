package com.tiagohenriqueramos.autocenter.service;
/*
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    @Value("${twilio.account.sid}")
    private String twilioAccountSid;

    @Value("${twilio.auth.token}")
    private String twilioAuthToken;

    @Value("${twilio.phone.number}")
    private String twilioPhoneNumber;

    public void sendSms(String recipientPhoneNumber, String message) {
        Twilio.init(twilioAccountSid, twilioAuthToken);

        Message twilioMessage = Message.creator(
                new PhoneNumber(recipientPhoneNumber),
                new PhoneNumber(twilioPhoneNumber),
                message
        ).create();

        System.out.println("SMS sent: " + twilioMessage.getSid());
    }
}*/
