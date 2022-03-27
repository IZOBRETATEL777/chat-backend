package com.izobretatel777.chat.controller;

import com.izobretatel777.chat.dto.ResetPasswordDto;
import com.izobretatel777.chat.service.ResetPasswordService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users/reset_password")
@Tag(name = "Reset password", description = "")
@RequiredArgsConstructor
public class ResetPasswordController {
    private final ResetPasswordService resetPasswordService;

    @GetMapping("{login}")
    public boolean sendResetPasswordEmail(@PathVariable String login) {
        return resetPasswordService.sendResetPasswordEmail(login);
    }

    @PostMapping
    public boolean resetPassword(@RequestBody ResetPasswordDto resetPasswordDto) {
        return resetPasswordService.resetPassword(resetPasswordDto);
    }
}
