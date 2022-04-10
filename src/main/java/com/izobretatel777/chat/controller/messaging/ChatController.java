package com.izobretatel777.chat.controller.messaging;

import com.izobretatel777.chat.dto.messaging.ChatRequestDto;
import com.izobretatel777.chat.dto.messaging.ChatResponseDto;
import com.izobretatel777.chat.service.messaging.ChatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("chats")
@Tag(name = "Chats", description = "Controller for CRUD manipulations on chats")
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @GetMapping()
    @PreAuthorize("hasAuthority('USER')")
    @Operation(
            summary = "Get all Chat ids",
            description = "Get all Chat ids for current user",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public List<Long> getChatsIds() {
        return chatService.getChats();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(
            summary = "Get info a Chat",
            description = "Get info about a Chat by its ID. User should be a member of that Chat",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public ChatResponseDto getChatById(@PathVariable long id) {
        return chatService.getChatById(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    @Operation(
            summary = "Create a new chat",
            description = "Create a new chat with user(s) in requestDto. Do not add current user in requestDto!",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public long createChat(@RequestBody ChatRequestDto chatRequestDto) {
        return chatService.createChat(chatRequestDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(
            summary = "Delete a Chat",
            description = "Delete a Chat by it ID. User should be a member of that Chat",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public void deleteChatById(@PathVariable long id) {
        chatService.deleteChatById(id);
    }
}
