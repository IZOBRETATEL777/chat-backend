package com.izobretatel777.chat.controller;

import com.izobretatel777.chat.dto.UserRequestDto;
import com.izobretatel777.chat.dto.UserResponseDto;
import com.izobretatel777.chat.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@Tag(name = "Users", description = "Controller for CRUD manipulations on users. Mostly for admins and clients (apps)")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(
            summary = "Get all Users",
            description = "Get all Users. Only for ADMINs.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public List<UserResponseDto> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/me")
    @Operation(
            summary = "Get current",
            description = "Get current User",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public UserResponseDto getUser(){
        return userService.getUser();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(
            summary = "Get a User",
            description = "Get a User by ID",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public UserResponseDto getUserById(@PathVariable long id) {
        return  userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(
            summary = "Delete a User",
            description = "Delete a User by ID. Only for ADMINs",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public void deleteUserById(@PathVariable long id){
        userService.deleteUserById(id);
    }
}

