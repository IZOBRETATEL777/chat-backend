package com.izobretatel777.chat.service.util;

import com.izobretatel777.chat.dao.entity.Key;

public interface KeyService {
    Key generateKey();
    Key getKeyByUserId(Long id);
    Key updateKeyByLogin(String login);
    String encrypt(String message, Key key);
    String decrypt(String message, Key key);
}
