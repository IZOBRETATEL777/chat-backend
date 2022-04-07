package com.izobretatel777.chat.service;

import com.izobretatel777.chat.dto.UserRequestDto;

public interface RegistrationService {
    void saveUser(UserRequestDto UserRequestDto);
    boolean activateUserByOtp(String otp);

}
