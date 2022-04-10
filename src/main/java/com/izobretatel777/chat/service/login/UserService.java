package com.izobretatel777.chat.service.login;

import com.izobretatel777.chat.dao.entity.User;
import com.izobretatel777.chat.dto.login.UserInfoRequestDto;
import com.izobretatel777.chat.dto.login.UserResponseDto;

import java.util.List;

public interface UserService {
    List<UserResponseDto> getUsers();
    UserResponseDto getUserById(Long id);
    void deleteUserById(Long id);
    UserResponseDto getCurrentlyLoggedUserDto();
    void updateUserInfo(UserInfoRequestDto userInfoRequestDto);
    User getCurrentlyLoggedUser();
}
