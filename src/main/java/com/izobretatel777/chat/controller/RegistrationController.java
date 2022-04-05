package com.izobretatel777.chat.controller;

import com.izobretatel777.chat.dto.UserRequestDto;
import com.izobretatel777.chat.service.RegistrationService;
import io.swagger.v3.oas.annotations.Operation;
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
                    Phone Number (optional) should not be greater than 15 and contains only digits, '+' and '-' signs.
                    If registration is done successfully, response will be 'true'; otherwise, 'false'.
                    """
    )
    public boolean saveUser(@RequestBody UserRequestDto userRequestDto){
        return registrationService.saveUser(userRequestDto);
    }

    @GetMapping("/activate/{otp}")
    @Operation(
            summary = "Activate user",
            description = "Activate a new User by 6-digit OTP code from the e-mail (login field of the User)." +
                    "If activation is done successfully, response will be 'true'; otherwise, 'false'."
    )
    public boolean activateUser(@PathVariable String otp){
        return registrationService.activateUserByOtp(otp);
    }
}
