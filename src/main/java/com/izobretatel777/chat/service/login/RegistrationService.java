package com.izobretatel777.chat.service.login;

import com.izobretatel777.chat.dto.login.UserRequestDto;

public interface RegistrationService {
    void saveUser(UserRequestDto UserRequestDto);
    boolean activateUserByOtp(String otp);

}
