package com.izobretatel777.chat.service.impl;

import com.izobretatel777.chat.dao.entity.Contact;
import com.izobretatel777.chat.dao.entity.User;
import com.izobretatel777.chat.dao.repo.ContactRepo;
import com.izobretatel777.chat.dao.repo.UserRepo;
import com.izobretatel777.chat.dto.messaging.ContactRequestDto;
import com.izobretatel777.chat.dto.messaging.ContactResponseDto;
import com.izobretatel777.chat.mapper.ContactMapper;
import com.izobretatel777.chat.service.login.UserService;
import com.izobretatel777.chat.service.messaging.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepo contactRepo;
    private final UserService userService;
    private final UserRepo userRepo;
    private final ContactMapper contactMapper;

    @Override
    public List<Long> getContacts() {
        Long userId = userService.getCurrentlyLoggedUser().getId();
        return contactRepo.findIdByOwnerId(userId);
    }

    @Override
    public ContactResponseDto getContactById(Long id) {
        Long userId = userService.getCurrentlyLoggedUser().getId();
        Contact contact = contactRepo.findByIdAndOwnerId(id, userId);
        if (contact != null)
            return contactMapper.toResponseDto(contact);
        else
            return new ContactResponseDto();
    }

    @Override
    public Long createContact(ContactRequestDto contactRequestDto) {
        User currentUser = userService.getCurrentlyLoggedUser();
        User friend = userRepo.findByLogin(contactRequestDto.getLogin());
        if (friend != null) {
            Contact contact = Contact.builder().owner(currentUser).userId(friend).build();
            return contactRepo.save(contact).getId();
        }
        else {
            return null;
        }
    }

    @Override
    public void deleteContactById(Long id) {
        Long userId = userService.getCurrentlyLoggedUser().getId();
        if (contactRepo.findByIdAndOwnerId(id, userId) != null)
            contactRepo.deleteById(id);
    }
}
