package com.izobretatel777.chat.service.impl;

import com.izobretatel777.chat.dao.entity.Chat;
import com.izobretatel777.chat.dao.entity.User;
import com.izobretatel777.chat.dao.repo.ChatRepo;
import com.izobretatel777.chat.dao.repo.UserRepo;
import com.izobretatel777.chat.dto.messaging.ChatRequestDto;
import com.izobretatel777.chat.dto.messaging.ChatResponseDto;
import com.izobretatel777.chat.mapper.ChatMapper;
import com.izobretatel777.chat.service.login.UserService;
import com.izobretatel777.chat.service.messaging.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Chat CRUD operations
@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    private final UserService userService;
    private final UserRepo userRepo;
    private final ChatRepo chatRepo;
    private final ChatMapper chatMapper;

    @Override
    public List<Long> getChats() {
        Long id = userService.getCurrentlyLoggedUser().getId();
        return chatRepo.findAllByUserId(id);
    }

    @Override
    public ChatResponseDto getChatById(Long id) {
        ChatResponseDto chatResponseDto = null;
        User user = userService.getCurrentlyLoggedUser();
        Optional<Chat> chatToFind = chatRepo.findById(id);
        if (chatToFind.isPresent() && chatToFind.get().getUsers().contains(user)) {
            chatResponseDto = chatMapper.toResponseDto(chatToFind.get());
        }
        return chatResponseDto;
    }

    @Override
    public Long createChat(ChatRequestDto chatRequestDto) {
        User currentUser = userService.getCurrentlyLoggedUser();
        Chat chat = Chat.builder().title(chatRequestDto.getTitle()).owner(currentUser)
                .users(userRepo.findAllById(chatRequestDto.getUsersIds())).build();
        chat.getUsers().add(currentUser);
        return chatRepo.save(chat).getId();
    }

    @Override
    public void deleteChatById(Long id) {
        User user = userService.getCurrentlyLoggedUser();
        Optional<Chat> chatToDelete = chatRepo.findById(id);
        if (chatToDelete.isPresent() && chatToDelete.get().getOwner().equals(user)) {
            chatRepo.delete(chatToDelete.get());
        }
    }
}
