package com.izobretatel777.chat.service;

public interface EmailingService {
    void sendEmail(String from, String to, String content);
}
