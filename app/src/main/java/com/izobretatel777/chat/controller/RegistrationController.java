package com.izobretatel777.chat.controller;

import com.izobretatel777.chat.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users/register")
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;

    @GetMapping("/activate/{otp}")
    private boolean activateUser(@PathVariable String otp){
        return userService.activateUserByOtp(otp);
    }
}
