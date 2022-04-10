package com.izobretatel777.chat.service.impl;

import com.izobretatel777.chat.dao.entity.User;
import com.izobretatel777.chat.dao.repo.UserRepo;
import com.izobretatel777.chat.dto.login.UpdatePasswordRequestDto;
import com.izobretatel777.chat.service.login.UpdatePasswordService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class UpdatePasswordServiceImpl implements UpdatePasswordService {
    private final EmailingServiceImpl emailingService;
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Value("${spring.mail.username}")
    private String fromEmail;

    // User validation
    private boolean isValidUserData(User user) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        return violations.isEmpty();
    }

    @Override
    public void sendResetPasswordEmail(String login) {
        User user = userRepo.findByLogin(login);
        // If user with this login (e-mail) not found, rise exception
        if (user == null)
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED);
        user.setOtp(RandomStringUtils.randomNumeric(6));
        // If by some reason, after setting OTP user become invalid rise exception
        if (!isValidUserData(user))
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
        // Otherwise, ues with new OTP and send e-mail letter with it
        userRepo.save(user);
        String content = "Hello from :Chat! messenger! Use this code to change your password:\n" + user.getOtp();
        emailingService.sendEmail(fromEmail, login, content);
    }

    @Override
    public void sendResetPasswordEmail() {
        // Get login from currently authorized user from Spring Security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        sendResetPasswordEmail(currentPrincipalName);
    }

    @Override
    public boolean resetPassword(UpdatePasswordRequestDto updatePasswordRequestDto) {
        User user = userRepo.findByOtp(updatePasswordRequestDto.getOtp());
        // If no users with this OTP found, return false
        if (user == null)
            return false;
        user.setPassword(updatePasswordRequestDto.getNewPassword());
        // If new password is too long or too short return false
        if (!isValidUserData(user))
            return false;
        // Otherwise continue
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Invalidate OTP
        user.setOtp(RandomStringUtils.randomNumeric(6));
        userRepo.save(user);
        return true;
    }
}
