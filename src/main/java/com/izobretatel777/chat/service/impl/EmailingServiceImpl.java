package com.izobretatel777.chat.service.impl;

import com.izobretatel777.chat.service.util.EmailingService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailingServiceImpl implements EmailingService {
    private final JavaMailSender mailSender;

    @Override
    public void sendEmail(String from, String to, String content) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setText(content);
        simpleMailMessage.setSubject(":Chat! message service");
        mailSender.send(simpleMailMessage);
    }
}
