package com.izobretatel777.chat.service.messaging;


import com.izobretatel777.chat.dto.messaging.ContactRequestDto;
import com.izobretatel777.chat.dto.messaging.ContactResponseDto;

import java.util.List;

public interface ContactService {
    List<Long> getContacts();
    ContactResponseDto getContactById(Long id);
    Long createContact(ContactRequestDto ContactRequestDto);
    void deleteContactById(Long id);
}
