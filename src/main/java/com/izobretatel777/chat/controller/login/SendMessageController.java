package com.izobretatel777.chat.controller.login;

import com.izobretatel777.chat.dto.messaging.MessageRequestDto;
import com.izobretatel777.chat.service.messaging.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "send")
@Tag(name = "Send message", description = "Controller for sending messages")
@RequiredArgsConstructor
public class SendMessageController {

    private final MessageService messageService;

    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    @Operation(
            summary = "Send a message",
            description = "Create and send a Message from current user",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public long createMessage(@RequestBody MessageRequestDto messageRequestDto) {
        return messageService.createMessage(messageRequestDto);
    }
}

