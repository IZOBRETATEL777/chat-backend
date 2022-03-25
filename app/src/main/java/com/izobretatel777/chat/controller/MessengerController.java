package com.izobretatel777.chat.controller;

import com.izobretatel777.chat.dto.MessageRequestDto;
import com.izobretatel777.chat.dto.MessageResponseDto;
import com.izobretatel777.chat.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "message", params = "chatId")
@RequiredArgsConstructor
public class MessengerController {
    private final MessageService messageService;

    @GetMapping()
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Get all messages by chat id in parameter", security = @SecurityRequirement(name = "bearerAuth"))
    List<Long> getMessagesByChatId(@RequestParam long chatId) {
        return messageService.getMessagesByChatId(chatId);
    }

    @GetMapping("/{messageId}")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Get message by id", security = @SecurityRequirement(name = "bearerAuth"))
    MessageResponseDto getMessageById(@RequestParam long chatId, @PathVariable long messageId) {
        return messageService.getMessageById(chatId, messageId);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Create message for chat", security = @SecurityRequirement(name = "bearerAuth"))
    long createMessage(@RequestBody MessageRequestDto messageRequestDto) {
        return messageService.createMessage(messageRequestDto);
    }

    @DeleteMapping("/{messageId}")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Delete message from chat", security = @SecurityRequirement(name = "bearerAuth"))
    void deleteMessageById(@RequestParam long chatId, @PathVariable long messageId) {
        messageService.deleteMessageById(chatId, messageId);
    }
}
