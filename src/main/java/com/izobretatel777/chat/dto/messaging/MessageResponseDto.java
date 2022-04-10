package com.izobretatel777.chat.dto.messaging;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class MessageResponseDto {
    Long authorId;
    Long chatId;
    String content;
    Date creationTime;
    boolean delivered;
}
