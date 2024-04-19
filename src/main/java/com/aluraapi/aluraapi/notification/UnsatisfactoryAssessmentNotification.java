package com.aluraapi.aluraapi.notification;

import com.aluraapi.aluraapi.domain.assessment.Assessment;

public class UnsatisfactoryAssessmentNotification {

    public static void instructorEmailSend(Assessment assessment){
        if(assessment.getGrade() < 6){
            EmailSender.send(assessment.getCourse().getInstuctor().getEmail(),
                    "Unsatisfactory Assessment",
                    "Course " + assessment.getCourse().getName() + " was evaluated with a grade below 6");
        }
    }
}
