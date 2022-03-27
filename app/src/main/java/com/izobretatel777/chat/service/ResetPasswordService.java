package com.izobretatel777.chat.service;

import com.izobretatel777.chat.dto.ResetPasswordDto;

public interface ResetPasswordService {
    boolean sendResetPasswordEmail(String login);
    boolean resetPassword(ResetPasswordDto resetPasswordDto);
}
