package com.statista.code.challenge.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmailService {
    private List<String> sentEmails;

    public EmailService() {
        sentEmails = new ArrayList<>();
    }
    public void send(String email)
    {
        sentEmails.add(email);
        System.out.println("Welcome email sent to: " + email);
    }
}
