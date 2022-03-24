package com.izobretatel777.chat.dao.repo;

import com.izobretatel777.chat.dao.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepo extends JpaRepository<Message, Long> {
}
