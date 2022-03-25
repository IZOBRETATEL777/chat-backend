package com.izobretatel777.chat.service;

import com.izobretatel777.chat.dto.MessageRequestDto;
import com.izobretatel777.chat.dto.MessageResponseDto;

import java.util.List;

public interface MessageService {
    List<Long> getMessagesByChatId(Long chatId);
    MessageResponseDto getMessageById(Long chatId, Long messageId);
    Long createMessage(MessageRequestDto messageRequestDto);
    void deleteMessageById(Long chatId, Long messageId);
    void updateMessageStatus(Long chatId, Long messageId);
}
