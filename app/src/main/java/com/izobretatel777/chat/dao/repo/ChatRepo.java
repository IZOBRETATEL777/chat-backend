package com.izobretatel777.chat.dao.repo;

import com.izobretatel777.chat.dao.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepo extends JpaRepository<Chat, Long> {

    @Query(value = "select id from chat_user join chats on chats.id=chat_user.chat_id where chat_user.user_id=?1", nativeQuery = true)
    List<Long> findAllByUserId(Long id);
}
