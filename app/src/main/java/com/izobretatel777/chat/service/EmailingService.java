package com.izobretatel777.chat.service;

public interface EmailingService {
    void sendActivationEmail(String from, String to, String content);
}
