package com.izobretatel777.chat.service.impl;

import com.izobretatel777.chat.dao.entity.User;
import com.izobretatel777.chat.dao.repo.UserRepo;
import com.izobretatel777.chat.dto.UpdatePasswordRequestDto;
import com.izobretatel777.chat.service.UpdatePasswordService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UpdatePasswordServiceImpl implements UpdatePasswordService {
    private final EmailingServiceImpl emailingService;
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Value("${spring.mail.username}")
    private String fromEmail;

    private boolean isValidUserData(User user) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        return violations.isEmpty();
    }

    @Override
    public boolean sendResetPasswordEmail(String login) {
        User user = userRepo.findByLogin(login);
        if (user == null)
            return false;
        user.setOtp(RandomStringUtils.randomNumeric(6));
        userRepo.save(user);
        String content = "Hello from :Chat! messenger! Use this code to change your password:\n" + user.getOtp();
        emailingService.sendEmail(fromEmail, login, content);
        return true;
    }

    @Override
    public boolean sendResetPasswordEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return sendResetPasswordEmail(currentPrincipalName);
    }

    @Override
    public boolean resetPassword(UpdatePasswordRequestDto updatePasswordRequestDto) {
        User user = userRepo.findByOtp(updatePasswordRequestDto.getOtp());
        if (user == null)
            return false;
        user.setPassword(updatePasswordRequestDto.getNewPassword());
        if (!isValidUserData(user))
            return false;
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setOtp(RandomStringUtils.randomNumeric(6));
        userRepo.save(user);
        return true;
    }
}
