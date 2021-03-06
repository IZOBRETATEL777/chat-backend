package com.izobretatel777.chat.service.impl;

import com.izobretatel777.chat.dao.entity.User;
import com.izobretatel777.chat.dao.repo.UserRepo;
import com.izobretatel777.chat.dto.login.UserRequestDto;
import com.izobretatel777.chat.service.util.EmailingService;
import com.izobretatel777.chat.service.util.KeyService;
import com.izobretatel777.chat.service.login.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    @Value("${spring.mail.activation-page}")
    private String activationPage;

    // Validation
    private boolean isValidUserData(User user) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        return userEntityRepository.findByLogin(user.getLogin()) == null && violations.isEmpty();
    }

    @Override
    public void saveUser(UserRequestDto userRequestDto) {
        // If user with this login exists throw exception
        if (userEntityRepository.findByLogin(userRequestDto.getLogin()) != null)
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        User user = new User();
        user.setLogin(userRequestDto.getLogin());
        user.setPassword(userRequestDto.getPassword());
        user.setActive(false);
        user.setKey(keyService.generateKey());
        user.setOtp(RandomStringUtils.randomNumeric(6));
        user.setName(userRequestDto.getName());
        user.setSurname(userRequestDto.getSurname());
        user.setPhoneNumber(userRequestDto.getPhoneNumber());
        // If data is not valid (incorrect phone number / email) format throw exception
        if (!isValidUserData(user))
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
        // Otherwise, continue and send e-mail letter with activation link
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userEntityRepository.save(user);
        emailingService.sendEmail(fromEmail, user.getLogin(), "Welcome to :Chat! messenger!" +
                " To activate your account, please, visit next link: " + activationPage + user.getOtp());
    }

    @Override
    public boolean activateUserByOtp(String otp) {
        User user = userEntityRepository.findByOtp(otp);
        // if user with this OTP is not found return false
        if (user == null || user.isActive())
            return false;
        // Otherwise, activate user
        user.setActive(true);
        user.setOtp(RandomStringUtils.randomNumeric(6));
        userEntityRepository.save(user);
        return true;
    }
}

