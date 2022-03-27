package com.izobretatel777.chat.controller;

import com.izobretatel777.chat.dto.ResetPasswordDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("reset_password")
@Tag(name = "Reset password", description = "")
@RequiredArgsConstructor
public class ResetPasswordController {
    @GetMapping("{email}")
    public boolean sendResetPasswordEmail(@PathVariable String email) {
        return false;
    }

    @PostMapping
    public boolean resetPassword(@RequestBody ResetPasswordDto resetPasswordDto) {
        return false;
    }
}
