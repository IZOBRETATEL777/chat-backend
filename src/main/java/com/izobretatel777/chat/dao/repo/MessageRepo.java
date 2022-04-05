package com.izobretatel777.chat.dao.repo;

import com.izobretatel777.chat.dao.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepo extends JpaRepository<Message, Long> {
    @Query(value = "select message.id from message join chat c on message.chat_id = c.id where message.chat_id=?1", nativeQuery = true)
    List<Long> findAllByChatId(Long id);

    Message getMessageByChat_IdAndId(Long chatId, Long id);
}
