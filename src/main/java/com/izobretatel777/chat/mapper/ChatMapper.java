package com.izobretatel777.chat.mapper;

import com.izobretatel777.chat.dao.entity.Chat;
import com.izobretatel777.chat.dao.entity.Message;
import com.izobretatel777.chat.dao.entity.User;
import com.izobretatel777.chat.dto.ChatResponseDto;
import jdk.jfr.Name;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ChatMapper {
    @Name("toResponseDto")
    default ChatResponseDto toResponseDto(Chat source) {
        return ChatResponseDto.builder()
                .title(source.getTitle())
                .messagesIds(source.getMessages().stream()
                        .map(Message::getId).collect(Collectors.toList()))
                .usersIds(source.getUsers().stream()
                        .map(User::getId).collect(Collectors.toList()))
                .build();
    }
}
