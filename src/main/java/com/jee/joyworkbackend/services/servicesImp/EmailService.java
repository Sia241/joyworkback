package com.jee.joyworkbackend.services.servicesImp;

import com.jee.joyworkbackend.entities.DayWork;
import com.jee.joyworkbackend.repositories.IDayWorkRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@EnableScheduling
@ComponentScan(basePackages = "com.mk.service")
@Component
public class EmailService {
    private final JavaMailSender javaMailSender;
    @Autowired
    private IDayWorkRepository dayWorkRepository;


    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    // Define a scheduled task to send daily mood survey emails at 18:00
    @Scheduled(cron = "0 26 20 ? * MON-FRI")
    public void sendDailyMoodSurveyEmails() {
        LocalDate currentDate = LocalDate.now();
        List<DayWork> dailyWorkData = dayWorkRepository.lReport(currentDate);
        for(DayWork d : dailyWorkData){
            String recipientEmail = d.getAppUser().getEmail(); // Replace with the employee's email
            String surveyLink = generateDailyMoodSurveyLink();
            sendDailyMoodSurveyEmail(recipientEmail, surveyLink);
        }

    }

    public void sendDailyMoodSurveyEmail(String recipientEmail, String surveyLink) {
        // Create and send the email
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(recipientEmail);
        mailMessage.setSubject("Daily Mood Survey");
        mailMessage.setText("Please take a moment to complete the daily mood survey: " + surveyLink);
        javaMailSender.send(mailMessage);
    }

    private String generateDailyMoodSurveyLink() {
        // Generate a unique token (e.g., using UUID)
        String token = UUID.randomUUID().toString();

        // Calculate the expiration timestamp, which is the current time + 15 minutes
        long expirationTimestamp = System.currentTimeMillis() + (15 * 60 * 1000); // 15 minutes in milliseconds

        // Create the survey link with the token and expiration timestamp
        return "http://localhost:4200/submitMood?token=" + token + "&timestamp=" + expirationTimestamp;
    }
}