package com.izobretatel777.chat.service.impl;

import com.izobretatel777.chat.dao.entity.User;
import com.izobretatel777.chat.dao.repo.UserRepo;
import com.izobretatel777.chat.dto.login.UserInfoRequestDto;
import com.izobretatel777.chat.dto.login.UserResponseDto;
import com.izobretatel777.chat.mapper.UserMapper;
import com.izobretatel777.chat.service.login.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

// User CRUD operations (without creating)
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
    public UserResponseDto getCurrentlyLoggedUserDto() {
        return userMapper.toResponseDto(getCurrentlyLoggedUser());
    }

    private boolean isValidUserData(User user) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        return violations.isEmpty();
    }

    @Override
    public void updateUserInfo(UserInfoRequestDto userInfoRequestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userEntityRepository.findByLogin(currentPrincipalName);
        user.setName(userInfoRequestDto.getName());
        user.setSurname(userInfoRequestDto.getSurname());
        user.setPhoneNumber(userInfoRequestDto.getPhoneNumber());
        if (!isValidUserData(user))
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
        else
            userEntityRepository.save(user);
    }

    // Get user from Spring Security context
    @Override
    public User getCurrentlyLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return userEntityRepository.findByLogin(currentPrincipalName);
    }
}
