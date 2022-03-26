package com.izobretatel777.chat.controller;

import com.izobretatel777.chat.dto.MessageResponseDto;
import com.izobretatel777.chat.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "messages", params = "chatId")
@Tag(name = "Message management", description = "Controller for managing messages. Chat id is required as parameter")
@RequiredArgsConstructor
public class MessengerController {
    private final MessageService messageService;

    @GetMapping()
    @PreAuthorize("hasAuthority('USER')")
    @Operation(
            summary = "Get all Message IDs",
            description = "Get all Message IDs in current chat (given as parameter)",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    List<Long> getMessagesByChatId(@RequestParam long chatId) {
        return messageService.getMessagesByChatId(chatId);
    }

    @GetMapping("/{messageId}")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(
            summary = "Get a Message",
            description = "Get a Message in current chat (ID of chat is given as parameter) by ID of that message ",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    MessageResponseDto getMessageById(@RequestParam long chatId, @PathVariable long messageId) {
        return messageService.getMessageById(chatId, messageId);
    }

    @DeleteMapping("/{messageId}")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(
            summary = "Delete a Message",
            description = "Delete a Message by its ID",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    void deleteMessageById(@RequestParam long chatId, @PathVariable long messageId) {
        messageService.deleteMessageById(chatId, messageId);
    }

    @PatchMapping("/{messageId}")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(
            summary = "Update status",
            description = "Update status of a Message to 'delivered' by its ID",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    void updateMessageStatus(@RequestParam long chatId, @PathVariable long messageId){
        messageService.updateMessageStatus(chatId, messageId);
    }
}
