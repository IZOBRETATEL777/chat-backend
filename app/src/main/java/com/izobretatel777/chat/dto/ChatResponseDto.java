package com.izobretatel777.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChatResponseDto {
    List<Long> usersIds;
    List<Long> messagesIds;
}
