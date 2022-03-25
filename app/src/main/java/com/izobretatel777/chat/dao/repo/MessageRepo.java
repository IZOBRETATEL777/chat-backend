package com.izobretatel777.chat.dao.repo;

import com.izobretatel777.chat.dao.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepo extends JpaRepository<Message, Long> {
    @Query(value = "select messages.id from messages join chats c on messages.chat_id = c.id where messages.chat_id=?1", nativeQuery = true)
    List<Long> findAllByChatId(Long id);

    Message getMessageByChat_IdAndId(Long chatId, Long id);
}
