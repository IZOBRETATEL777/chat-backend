package com.izobretatel777.chat.mapper;

import com.izobretatel777.chat.dao.entity.Message;
import com.izobretatel777.chat.dto.messaging.MessageResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    @Named("toResponseDto")
    default MessageResponseDto toResponseDto(Message source) {
        return MessageResponseDto.builder().delivered(source.isDelivered()).authorId(source.getAuthor().getId()).chatId(source.getChat().getId())
                .content(source.getContent()).creationTime(source.getCreationTime()).build();
    }
}
