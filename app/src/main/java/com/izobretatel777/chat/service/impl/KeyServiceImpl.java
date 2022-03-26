package com.izobretatel777.chat.service.impl;

import com.izobretatel777.chat.dao.entity.Key;
import com.izobretatel777.chat.dao.repo.KeyRepo;
import com.izobretatel777.chat.service.KeyService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KeyServiceImpl implements KeyService {

    private final KeyRepo keyRepo;

    @Override
    public Key generateKey() {
        Key key = new Key();
        key.setValue(RandomStringUtils.randomAlphabetic(10));
        keyRepo.save(key);
        return key;
    }

    @Override
    public Key getKeyByLogin(String login) {
        return keyRepo.findByUserLogin(login);
    }

    @Override
    public String encrypt(String message, String key) {
        String res = "";
        message = message.toUpperCase();
        for (int i = 0, j = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            if (c < 'A' || c > 'Z') continue;
            res += (char)((c + key.charAt(j) - 2 * 'A') % 26 + 'A');
            j = ++j % key.length();
        }
        return res;
    }

    public String decrypt(String message, String key) {
        String res = "";
        message = message.toUpperCase();
        for (int i = 0, j = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            if (c < 'A' || c > 'Z') continue;
            res += (char)((c - key.charAt(j) + 26) % 26 + 'A');
            j = ++j % key.length();
        }
        return res;
    }
}
