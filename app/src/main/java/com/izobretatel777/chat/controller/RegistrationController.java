package com.izobretatel777.chat.controller;

import com.izobretatel777.chat.dto.UserRequestDto;
import com.izobretatel777.chat.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users/register")
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;

    @GetMapping("/activate/{otp}")
    private boolean activateUser(@PathVariable String otp){
        return userService.activateUserByOtp(otp);
    }

    @PostMapping
    @Operation(
            summary = "Register",
            description = "Save a new User (register)"
    )
    public boolean saveUser(@RequestBody UserRequestDto userRequestDto){
        return userService.saveUser(userRequestDto);
    }
}
