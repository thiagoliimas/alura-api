package com.aluraapi.aluraapi.notification;

import com.aluraapi.aluraapi.domain.assessment.Assessment;
import com.aluraapi.aluraapi.domain.courses.Course;
import org.springframework.context.annotation.Bean;

public class EmailSender {

    public static void send(String recipientEmail, String subject, String body) {
        System.out.printf("Simulating sending email to [%s]:\n%n", recipientEmail);
        System.out.printf(" Subject: %s Body: %s %n", subject, body);
    }
}
