package com.izobretatel777.chat.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChangePasswordRequestDto {
    String otp;
    String newPassword;
}
