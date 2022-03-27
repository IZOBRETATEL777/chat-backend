package com.izobretatel777.chat.controller;

import com.izobretatel777.chat.dto.ResetPasswordDto;
import com.izobretatel777.chat.service.ResetPasswordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users/reset_password")
@Tag(name = "Reset password", description =
        """
        Controller for resetting password. User have to query Login (only e-mail) and receive OTP on his/her mail.
        Using this OTP, request with new password should be sent.
        """)
@RequiredArgsConstructor
public class ResetPasswordController {
    private final ResetPasswordService resetPasswordService;

    @GetMapping("{login}")
    @Operation(
            summary = "Send an reset password e-mail",
            description = """
                    Send an email with OTP by User login (email).
                    Responds 'true' if login exists; 'false' otherwise.
                    """)
    public boolean sendResetPasswordEmail(@PathVariable String login) {
        return resetPasswordService.sendResetPasswordEmail(login);
    }

    @PostMapping
    @Operation(
            summary = "Reset password",
            description = """
                    Reset Password using OTP from e-mail letter
                    Responds 'true' if OTP is correct and password is acceptable; 'false' otherwise.
                    """)
    public boolean resetPassword(@RequestBody ResetPasswordDto resetPasswordDto) {
        return resetPasswordService.resetPassword(resetPasswordDto);
    }
}
