package com.izobretatel777.chat.service.impl;

import com.izobretatel777.chat.dao.entity.Message;
import com.izobretatel777.chat.dao.entity.User;
import com.izobretatel777.chat.dao.repo.ChatRepo;
import com.izobretatel777.chat.dao.repo.MessageRepo;
import com.izobretatel777.chat.dao.repo.UserRepo;
import com.izobretatel777.chat.dto.MessageRequestDto;
import com.izobretatel777.chat.dto.MessageResponseDto;
import com.izobretatel777.chat.mapper.MessageMapper;
import com.izobretatel777.chat.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepo messageRepo;
    private final ChatRepo chatRepo;
    private final UserRepo userRepo;
    private final MessageMapper messageMapper;

    @Override
    public List<Long> getMessagesByChatId(Long chatId) {
        return messageRepo.findAllByChatId(chatId);
    }

    @Override
    public MessageResponseDto getMessageById(Long chatId, Long messageId) {
        Message message = messageRepo.getMessageByChat_IdAndId(chatId, messageId);
        return messageMapper.toResponseDto(message);
    }

    @Override
    public Long createMessage(MessageRequestDto messageRequestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userRepo.findByLogin(currentPrincipalName);
        Message message = Message.builder().chat(chatRepo.getById(messageRequestDto.getChatId())).author(user)
                .content(messageRequestDto.getContent()).creationTime(new Date(System.currentTimeMillis())).build();
        return messageRepo.save(message).getId();
    }

    @Override
    public void deleteMessageById(Long chatId, Long messageId) {
        Message message = messageRepo.getMessageByChat_IdAndId(chatId, messageId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userRepo.findByLogin(currentPrincipalName);
        if (user == message.getAuthor())
            messageRepo.delete(message);
    }

    @Override
    public void updateMessageStatus(Long chatId, Long messageId) {
        Message message = messageRepo.getMessageByChat_IdAndId(chatId, messageId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userRepo.findByLogin(currentPrincipalName);
        if (user == message.getAuthor()) {
            message.setDelivered(true);
            messageRepo.save(message);
        }
    }
}
