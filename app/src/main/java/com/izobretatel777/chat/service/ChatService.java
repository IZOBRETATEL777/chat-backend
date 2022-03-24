package com.izobretatel777.chat.service;

import com.izobretatel777.chat.dto.ChatResponseDto;
import com.izobretatel777.chat.dto.ChatRequestDto;

import java.util.List;

public interface ChatService {
    List<Long> getChats();
    ChatResponseDto getChatById(Long id);
    Long createChat(ChatRequestDto ChatRequestDto);
    void deleteChatById(Long id);
}
