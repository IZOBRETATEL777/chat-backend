package com.izobretatel777.chat.service;

import com.izobretatel777.chat.dao.entity.Key;

public interface KeyService {
    Key generateKey();
    Key getKeyByLogin(String login);
    String encrypt(String key, String message);
    String decrypt(String key, String message);
}
