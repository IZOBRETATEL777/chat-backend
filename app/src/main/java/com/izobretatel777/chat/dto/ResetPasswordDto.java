package com.izobretatel777.chat.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResetPasswordDto {
    String otp;
    String newPassword;
}
