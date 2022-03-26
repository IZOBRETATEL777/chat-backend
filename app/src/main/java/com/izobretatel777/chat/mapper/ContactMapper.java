package com.izobretatel777.chat.mapper;

import com.izobretatel777.chat.dao.entity.Contact;
import com.izobretatel777.chat.dto.ContactResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ContactMapper {

    @Named("toResponseDto")
    default ContactResponseDto toResponseDto(Contact source) {
        return ContactResponseDto.builder().contactId(source.getId()).userId(source.getId()).build();
    }
}
