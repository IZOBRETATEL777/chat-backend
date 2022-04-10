package com.izobretatel777.chat.controller.management;

import com.izobretatel777.chat.dto.login.UpdatePasswordRequestDto;
import com.izobretatel777.chat.service.login.UpdatePasswordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
                    """,
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @GetMapping
    @PreAuthorize("hasAuthority('USER')")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful password update"),
            @ApiResponse(responseCode = "401", description = "No user found"),
            @ApiResponse(responseCode = "406", description = "Not all requirements are followed " +
                    "(password is too long, etc")
    })
    public void sendUpdatePasswordEmail() {
        updatePasswordService.sendResetPasswordEmail();
    }

    @PostMapping
    @Operation(
            summary = "Update password",
            description = """
                    Update Password using OTP from e-mail letter
                    Controller returns corresponding message about password change.
                    """,
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @PreAuthorize("hasAuthority('USER')")
    public String updatePassword(@RequestBody UpdatePasswordRequestDto updatePasswordRequestDto) {
        if (updatePasswordService.resetPassword(updatePasswordRequestDto))
            return "<h1>Successful password update</h1>";
        else
            return "<h1>Unsuccessful password update</h1>";
    }
}
