package com.izobretatel777.chat.controller;

import com.izobretatel777.chat.dto.MessageRequestDto;
import com.izobretatel777.chat.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "send")
@RequiredArgsConstructor
public class SendMessageController {

    private final MessageService messageService;

    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Send message", security = @SecurityRequirement(name = "bearerAuth"))
    long createMessage(@RequestBody MessageRequestDto messageRequestDto) {
        return messageService.createMessage(messageRequestDto);
    }
}
