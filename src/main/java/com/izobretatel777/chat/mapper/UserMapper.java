package com.izobretatel777.chat.mapper;

import com.izobretatel777.chat.dao.entity.User;
import com.izobretatel777.chat.dto.UserResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    List<UserResponseDto> toResponseDto(List<User> source);
    UserResponseDto toResponseDto(User source);
}
