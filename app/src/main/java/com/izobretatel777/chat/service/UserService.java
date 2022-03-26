package com.izobretatel777.chat.service;

import com.izobretatel777.chat.dto.UserRequestDto;
import com.izobretatel777.chat.dto.UserResponseDto;

import java.util.List;

public interface UserService {
    List<UserResponseDto> getUsers();
    UserResponseDto getUserById(Long id);
    Long saveUser(UserRequestDto UserRequestDto);
    boolean activateUserByOtp(String otp);
    void deleteUserById(Long id);
    UserResponseDto getUser();
}
