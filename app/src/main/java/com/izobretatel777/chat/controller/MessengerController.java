package com.izobretatel777.Message.controller;

import com.izobretatel777.chat.dto.MessageRequestDto;
import com.izobretatel777.chat.dto.MessageResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("message")
@RequiredArgsConstructor
public class MessengerController {
    @GetMapping()
    List<MessageResponseDto> getMessages() {
        return null;
    }

    @GetMapping("/{id}")
    MessageResponseDto getMessageById(@PathVariable long id) {
        return null;
    }

    @PostMapping
    long createMessage(@RequestBody MessageRequestDto MessageRequestDto) {
        return 0;
    }

    @DeleteMapping("/{id}")
    void deleteMessageById(@PathVariable long id) {

    }
}
