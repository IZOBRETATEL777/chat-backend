package com.izobretatel777.chat.controller;

import com.izobretatel777.chat.dto.UpdatePasswordRequestDto;
import com.izobretatel777.chat.service.UpdatePasswordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
    private final UpdatePasswordService resetPasswordService;

    @GetMapping("{login}")
    @Operation(
            summary = "Send an reset password e-mail",
            description = """
                    Send an email with OTP by User login (email).
                    Responds 'true' if login exists; 'false' otherwise.
                    """)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful password reset"),
            @ApiResponse(responseCode = "401", description = "No user found"),
            @ApiResponse(responseCode = "406", description = "Not all requirements are followed " +
                    "(password is too long, etc")
    })
    public void sendResetPasswordEmail(@PathVariable String login) {
        resetPasswordService.sendResetPasswordEmail(login);
    }
    @PostMapping
    @Operation(
            summary = "Reset password",
            description = """
                    Reset Password using OTP from e-mail letter
                    Controller returns corresponding message about password reset.
                    """)
    public String resetPassword(@RequestBody UpdatePasswordRequestDto updatePasswordRequestDto) {
        if (resetPasswordService.resetPassword(updatePasswordRequestDto))
            return "<h1>Successful password reset</h1>";
        else
            return "<h1>Unsuccessful password reset</h1>";
    }
}
