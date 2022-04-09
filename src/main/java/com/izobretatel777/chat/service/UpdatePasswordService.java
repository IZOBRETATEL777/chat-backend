package com.izobretatel777.chat.service;

import com.izobretatel777.chat.dto.UpdatePasswordRequestDto;

public interface UpdatePasswordService {
    boolean sendResetPasswordEmail(String login);
    boolean sendResetPasswordEmail();
    boolean resetPassword(UpdatePasswordRequestDto updatePasswordRequestDto);
}
