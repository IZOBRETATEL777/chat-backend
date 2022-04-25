package com.izobretatel777.chat.dto.messaging;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class MessageResponseDto {
    Long authorId;
    Long chatId;
    String content;
    Timestamp creationTime;
    boolean delivered;
}
