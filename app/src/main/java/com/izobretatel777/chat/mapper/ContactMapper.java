package com.izobretatel777.chat.mapper;

import com.izobretatel777.chat.dao.entity.Contact;
import com.izobretatel777.chat.dto.ContactResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContactMapper {
    List<ContactResponseDto> toResponseDto(List<Contact> source);
    ContactResponseDto toResponseDto(Contact source);
}
