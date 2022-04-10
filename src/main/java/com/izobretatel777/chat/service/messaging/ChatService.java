package com.izobretatel777.chat.service.messaging;

import com.izobretatel777.chat.dto.messaging.ChatResponseDto;
import com.izobretatel777.chat.dto.messaging.ChatRequestDto;

import java.util.List;

public interface ChatService {
    List<Long> getChats();
    ChatResponseDto getChatById(Long id);
    Long createChat(ChatRequestDto ChatRequestDto);
    void deleteChatById(Long id);
}
