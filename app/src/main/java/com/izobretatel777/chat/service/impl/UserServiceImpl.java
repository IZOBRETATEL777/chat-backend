package com.izobretatel777.chat.service.impl;

import com.izobretatel777.chat.dao.entity.Key;
import com.izobretatel777.chat.dao.entity.User;
import com.izobretatel777.chat.dao.repo.UserRepo;
import com.izobretatel777.chat.dto.UserResponseDto;
import com.izobretatel777.chat.mapper.UserMapper;
import com.izobretatel777.chat.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userEntityRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserResponseDto> getUsers() {
        List<User> entities = userEntityRepository.findAll().stream().filter(User::isActive).collect(Collectors.toList());
        return userMapper.toResponseDto(entities);
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        Optional<User> entity = userEntityRepository.findById(id);
        if (entity.isEmpty()) {
            return null;
        }
        User user = entity.get();
        UserResponseDto response = null;
        if (user.isActive()) {
            response = userMapper.toResponseDto(user);
        }
        return response;
    }

    @Override
    public void deleteUserById(Long id) {
        Optional<User> entity = userEntityRepository.findById(id);
        if (entity.isEmpty()) {
            return;
        }
        User user = entity.get();
        user.setActive(false);
        userEntityRepository.save(user);
    }

    @Override
    public UserResponseDto getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return userMapper.toResponseDto(userEntityRepository.findByLogin(currentPrincipalName));
    }
}
