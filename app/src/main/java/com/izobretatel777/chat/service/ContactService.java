package com.izobretatel777.chat.service;


import com.izobretatel777.chat.dto.ContactRequestDto;
import com.izobretatel777.chat.dto.ContactResponseDto;

import java.util.List;

public interface ContactService {
    List<Long> getContacts();
    ContactResponseDto getContactById(Long id);
    Long createContact(ContactRequestDto ContactRequestDto);
    void deleteContactById(Long id);
}
