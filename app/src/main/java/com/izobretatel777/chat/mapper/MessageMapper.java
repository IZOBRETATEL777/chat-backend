package com.izobretatel777.chat.mapper;

import com.izobretatel777.chat.dao.entity.Message;
import com.izobretatel777.chat.dto.MessageResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    List<MessageResponseDto> toResponseDto(List<Message> source);
    MessageResponseDto toResponseDto(Message source);
}
