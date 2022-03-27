package com.izobretatel777.chat.controller;

import com.izobretatel777.chat.dto.ChangePasswordRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/password_recovery")
public class PasswordRecoveryController {
    @PostMapping("/send_recovery_email")
    public boolean sendRecoveryEmail() {
        return false;
    }
    @PostMapping("/change_password")
    public boolean changePassword(@RequestBody ChangePasswordRequestDto changePasswordRequestDto) {
        return false;
    }
}
