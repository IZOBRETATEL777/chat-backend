package com.izobretatel777.chat.controller;

import com.izobretatel777.chat.dto.UpdatePasswordRequestDto;
import com.izobretatel777.chat.service.UpdatePasswordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users/update_password")
@Tag(name = "Update password controller", description =
        """
        Controller for updating password by resetting it. User have to query Login (only e-mail) and receive OTP on his/her mail.
        Using this OTP, request with new password should be sent.
        """)
@RequiredArgsConstructor
public class UpdatePasswordController {
    private final UpdatePasswordService updatePasswordService;

    @Operation(
            summary = "Send an update password e-mail",
            description = """
                    Send an email with OTP by User login (email).
                    Responds 'true' if login exists; 'false' otherwise.
                    """)
    @GetMapping
    @PreAuthorize("hasAuthority('USER')")
    public boolean sendUpdatePasswordEmail() {
        return updatePasswordService.sendResetPasswordEmail();
    }

    @PostMapping
    @Operation(
            summary = "Update password",
            description = """
                    Update Password using OTP from e-mail letter
                    Controller returns corresponding message about password change.
                    """)
    @PreAuthorize("hasAuthority('USER')")
    public String updatePassword(@RequestBody UpdatePasswordRequestDto updatePasswordRequestDto) {
        if (updatePasswordService.resetPassword(updatePasswordRequestDto))
            return "<h1>Successful password update</h1>";
        else
            return "<h1>Unsuccessful password update</h1>";
    }
}
