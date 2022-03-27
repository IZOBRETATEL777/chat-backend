package com.izobretatel777.chat.controller;

import com.izobretatel777.chat.dto.UserRequestDto;
import com.izobretatel777.chat.service.RegistrationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users/register")
@RequiredArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;

    @GetMapping("/activate/{otp}")
    public boolean activateUser(@PathVariable String otp){
        return registrationService.activateUserByOtp(otp);
    }

    @PostMapping
    @Operation(
            summary = "Register",
            description = "Save a new User (register)"
    )
    public boolean saveUser(@RequestBody UserRequestDto userRequestDto){
        return registrationService.saveUser(userRequestDto);
    }
}
