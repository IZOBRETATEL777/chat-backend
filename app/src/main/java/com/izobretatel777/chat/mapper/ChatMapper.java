package com.izobretatel777.chat.mapper;

import com.izobretatel777.chat.dao.entity.Chat;
import com.izobretatel777.chat.dto.ChatResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChatMapper {
    List<ChatResponseDto> toResponseDto(List<Chat> source);
    ChatResponseDto toResponseDto(Chat source);
}
