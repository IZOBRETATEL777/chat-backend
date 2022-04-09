package com.izobretatel777.chat.service;

import com.izobretatel777.chat.dto.UpdatePasswordRequestDto;

public interface UpdatePasswordService {
    void sendResetPasswordEmail(String login);
    void sendResetPasswordEmail();
    boolean resetPassword(UpdatePasswordRequestDto updatePasswordRequestDto);
}
