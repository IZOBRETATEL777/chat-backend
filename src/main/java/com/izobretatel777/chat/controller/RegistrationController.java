package com.izobretatel777.chat.controller;

import com.izobretatel777.chat.dto.UserRequestDto;
import com.izobretatel777.chat.service.RegistrationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users/register")
@Tag(name = "Registration", description = "Controller for register a new User and activate him/her")
@RequiredArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;

    @PostMapping
    @Operation(
            summary = "Register",
            description = """
                    Save a new User (register). Login and password should not be empty.
                    Login should be an valid e-mail address. Length should not be greater that 255.
                    Password should be less than 6 and not be greater than 10.
                    Name (optional) should not be greater than 30.
                    Surname (optional) should not be greater than 50.
                    Phone Number (optional) should not be less than 9 and not be greater than 15 and contains only digits, '+' and '-' signs.
                    """
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful registration"),
            @ApiResponse(responseCode = "406", description = "Not all requirements are followed " +
                    "(incorrect phone number format, login format, etc"),
            @ApiResponse(responseCode = "409", description = "Login is already used")
    })
    public void saveUser(@RequestBody UserRequestDto userRequestDto){
        registrationService.saveUser(userRequestDto);
    }

    @GetMapping("/activate/{otp}")
    @Operation(
            summary = "Activate user",
            description = """
                    Activate a new User by 6-digit OTP code from the e-mail (login field of the User).
                    Controller returns corresponding message about activation.
                    """
    )
    public String activateUser(@PathVariable String otp) {
        if (registrationService.activateUserByOtp(otp))
            return "<h1>Successful activation</h1>";
        else
            return "<h1>Unsuccessful activation</h1>";
    }
}
