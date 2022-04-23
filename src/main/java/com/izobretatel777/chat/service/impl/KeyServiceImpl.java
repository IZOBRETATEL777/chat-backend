package com.izobretatel777.chat.service.impl;

import com.izobretatel777.chat.dao.entity.Key;
import com.izobretatel777.chat.dao.repo.KeyRepo;
import com.izobretatel777.chat.service.util.KeyService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

// Service responsible for additional crypto operations on messages
// Vigenere cipher is used
@Service
@RequiredArgsConstructor
public class KeyServiceImpl implements KeyService {

    private final KeyRepo keyRepo;
    // Using 64-bit encryption
    private final static long ALPHABET_SIZE = Long.MAX_VALUE;

    @Override
    public Key generateKey() {
        Key key = new Key();
        // Generate random string
        key.setValue(RandomStringUtils.randomPrint(10));
        keyRepo.save(key);
        return key;
    }

    @Override
    public Key getKeyByUserId(Long id) {
        return keyRepo.findKeyByUserId(id);
    }

    @Override
    public Key updateKeyByLogin(String login) {
        Key key = keyRepo.findByUserLogin(login);
        keyRepo.save(generateKey());
        return key;
    }

    // Encryption using Vigenere Cipher
    @Override
    public String encrypt(String message, Key key) {
        StringBuilder encryptedMessage = new StringBuilder();
        int keyIndex = 0;
        //E_i = (P_i + K_i) % ALPH_LENGTH
        for (int i = 0; i < message.length(); i++) {
            // If at the end of key
            if (keyIndex == key.getValue().length()) {
                keyIndex = 0;
            }
            // Getting char codes
            long charMessage = message.charAt(i);
            long charKey = key.getValue().charAt(keyIndex);
            // Add to result
            encryptedMessage.append((char) Math.floorMod((charMessage + charKey), ALPHABET_SIZE));
            keyIndex++;
        }
        return encryptedMessage.toString();
    }

    // Decryption using Vigenere Cipher
    @Override
    public String decrypt(String message, Key key) {
        StringBuilder encryptedMessage = new StringBuilder();
        int keyIndex = 0;
        //E_i = (P_i - K_i) % ALPH_LENGTH
        for (int i = 0; i < message.length(); i++) {
            // If at the end of key
            if (keyIndex == key.getValue().length()) {
                keyIndex = 0;
            }
            // Getting char codes
            long charMessage = message.charAt(i);
            long charKey = key.getValue().charAt(keyIndex);
            // Add to result
            encryptedMessage.append((char) Math.floorMod((charMessage - charKey), ALPHABET_SIZE));
            keyIndex++;
        }
        return encryptedMessage.toString();
    }
}