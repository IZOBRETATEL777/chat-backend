package com.izobretatel777.chat.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ChatResponseDto {
    String title;
    List<Long> usersIds;
    List<Long> messagesIds;
}
