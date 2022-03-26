package com.izobretatel777.chat.dao.repo;

import com.izobretatel777.chat.dao.entity.Key;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeyRepo extends JpaRepository<Key, Long> {
    Key findKeyByUserId(Long userId);
    Key findByUserLogin(String userLogin);
}
