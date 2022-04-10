package com.izobretatel777.chat.service.login;

import com.izobretatel777.chat.dto.login.UpdatePasswordRequestDto;

public interface UpdatePasswordService {
    void sendResetPasswordEmail(String login);
    void sendResetPasswordEmail();
    boolean resetPassword(UpdatePasswordRequestDto updatePasswordRequestDto);
}
