package com.izobretatel777.chat.service.impl;

import com.izobretatel777.chat.dao.entity.Chat;
import com.izobretatel777.chat.dao.entity.User;
import com.izobretatel777.chat.dao.repo.ChatRepo;
import com.izobretatel777.chat.dao.repo.UserRepo;
import com.izobretatel777.chat.dto.ChatRequestDto;
import com.izobretatel777.chat.dto.ChatResponseDto;
import com.izobretatel777.chat.mapper.ChatMapper;
import com.izobretatel777.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    private final UserRepo userRepo;
    private final ChatRepo chatRepo;
    private final ChatMapper chatMapper;

    @Override
    public List<Long> getChats() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Long id = userRepo.findIdByLogin(currentPrincipalName);
        return chatRepo.findAllByUserId(id);
    }

    @Override
    public ChatResponseDto getChatById(Long id) {
        ChatResponseDto chatResponseDto = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userRepo.findByLogin(currentPrincipalName);
        Optional<Chat> chatToFind = chatRepo.findById(id);
        if (chatToFind.isPresent() && chatToFind.get().getUsers().contains(user)) {
            chatResponseDto = chatMapper.toResponseDto(chatToFind.get());
        }
        return chatResponseDto;
    }

    @Override
    public Long createChat(ChatRequestDto chatRequestDto) {
        Chat chat = new Chat();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User currentUser = userRepo.findByLogin(currentPrincipalName);
        chat.getUsers().add(currentUser);
        chat.getUsers().addAll(userRepo.findAllById(chatRequestDto.getUsersIds()));
        return chatRepo.save(chat).getId();
    }

    @Override
    public void deleteChatById(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userRepo.findByLogin(currentPrincipalName);
        Optional<Chat> chatToDelete = chatRepo.findById(id);
        if (chatToDelete.isPresent() && chatToDelete.get().getUsers().contains(user)) {
            chatRepo.delete(chatToDelete.get());
        }
    }
}
