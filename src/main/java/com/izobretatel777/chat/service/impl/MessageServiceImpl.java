package com.izobretatel777.chat.service.impl;

import com.izobretatel777.chat.dao.entity.Key;
import com.izobretatel777.chat.dao.entity.Message;
import com.izobretatel777.chat.dao.entity.User;
import com.izobretatel777.chat.dao.repo.ChatRepo;
import com.izobretatel777.chat.dao.repo.MessageRepo;
import com.izobretatel777.chat.dao.repo.UserRepo;
import com.izobretatel777.chat.dto.messaging.MessageRequestDto;
import com.izobretatel777.chat.dto.messaging.MessageResponseDto;
import com.izobretatel777.chat.mapper.MessageMapper;
import com.izobretatel777.chat.service.login.UserService;
import com.izobretatel777.chat.service.util.KeyService;
import com.izobretatel777.chat.service.messaging.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

// Messages CRUD operations
@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepo messageRepo;
    private final ChatRepo chatRepo;
    private final UserService userService;
    private final MessageMapper messageMapper;
    private final KeyService keyService;

    @Override
    public List<Long> getMessagesByChatId(Long chatId) {
        return messageRepo.findAllByChatId(chatId);
    }

    @Override
    public MessageResponseDto getMessageById(Long chatId, Long messageId) {
        Long id = userService.getCurrentlyLoggedUser().getId();
        Key key = keyService.getKeyByUserId(id);
        Message message = messageRepo.getMessageByChat_IdAndId(chatId, messageId);
        String encryptedContent = keyService.encrypt(message.getContent(), key);
        message.setContent(encryptedContent);
        return messageMapper.toResponseDto(message);
    }

    @Override
    public Long createMessage(MessageRequestDto messageRequestDto) {
        User user = userService.getCurrentlyLoggedUser();
        String content = messageRequestDto.getContent();
        if (messageRequestDto.isEncrypted())
            content = keyService.decrypt(content, keyService.getKeyByUserId(user.getId()));
        Message message = Message.builder().chat(chatRepo.getById(messageRequestDto.getChatId())).author(user)
                .content(content).creationTime(new Date(System.currentTimeMillis())).build();
        return messageRepo.save(message).getId();
    }

    @Override
    public void deleteMessageById(Long chatId, Long messageId) {
        Message message = messageRepo.getMessageByChat_IdAndId(chatId, messageId);
        User user = userService.getCurrentlyLoggedUser();
        if (user == message.getAuthor())
            messageRepo.delete(message);
    }

    @Override
    public void updateMessageStatus(Long chatId, Long messageId) {
        Message message = messageRepo.getMessageByChat_IdAndId(chatId, messageId);
        User user = userService.getCurrentlyLoggedUser();
        if (user == message.getAuthor()) {
            message.setDelivered(true);
            messageRepo.save(message);
        }
    }
}
