package com.izobretatel777.chat.controller;

import com.izobretatel777.chat.dto.ChatRequestDto;
import com.izobretatel777.chat.dto.ChatResponseDto;
import com.izobretatel777.chat.service.ChatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("chat")
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @GetMapping()
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Get all chat ids for current user", security = @SecurityRequirement(name = "bearerAuth"))
    List<Long> getChatsIds() {
        return chatService.getChats();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Get info about current chat", security = @SecurityRequirement(name = "bearerAuth"))
    ChatResponseDto getChatById(@PathVariable long id) {
        return chatService.getChatById(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Create a new chat. Do not add current user in requestDto", security = @SecurityRequirement(name = "bearerAuth"))
    long createChat(@RequestBody ChatRequestDto chatRequestDto) {
        return chatService.createChat(chatRequestDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Delete chat", security = @SecurityRequirement(name = "bearerAuth"))
    void deleteChatById(@PathVariable long id) {
        chatService.deleteChatById(id);
    }
}
