package com.izobretatel777.chat.service.impl;

import com.izobretatel777.chat.dao.entity.User;
import com.izobretatel777.chat.dao.repo.UserRepo;
import com.izobretatel777.chat.dto.UserRequestDto;
import com.izobretatel777.chat.service.EmailingService;
import com.izobretatel777.chat.service.KeyService;
import com.izobretatel777.chat.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private final UserRepo userEntityRepository;
    private final PasswordEncoder passwordEncoder;
    private final KeyService keyService;
    private final EmailingService emailingService;


    @Value("${spring.mail.username}")
    private String fromEmail;

    @Value("${current.url}")
    private String currentUrl;

    private boolean isValidUserData(User user) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        return userEntityRepository.findByLogin(user.getLogin()) == null && violations.isEmpty();
    }

    @Override
    public boolean saveUser(UserRequestDto userRequestDto) {
        User user = new User();
        user.setLogin(userRequestDto.getLogin());
        user.setPassword(userRequestDto.getPassword());
        user.setActive(false);
        user.setKey(keyService.generateKey());
        user.setOtp(RandomStringUtils.randomNumeric(6));
        user.setName(userRequestDto.getName());
        user.setSurname(userRequestDto.getSurname());
        user.setPhoneNumber(userRequestDto.getPhoneNumber());
        if (!isValidUserData(user))
            return false;
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userEntityRepository.save(user);
        emailingService.sendActivationEmail(fromEmail, user.getLogin(), "Welcome to :Chat! messenger!" +
                " To activate your account, please, visit next link: " +
                            currentUrl + "/users/register/activate/" + user.getOtp());
        return true;
    }

    @Override
    public boolean activateUserByOtp(String otp) {
        User user = userEntityRepository.findByOtp(otp);
        if (user == null || user.isActive())
            return false;
        user.setActive(true);
        userEntityRepository.save(user);
        return true;
    }
}
