package com.izobretatel777.chat.service.impl;

import com.izobretatel777.chat.dao.entity.Contact;
import com.izobretatel777.chat.dao.entity.User;
import com.izobretatel777.chat.dao.repo.ContactRepo;
import com.izobretatel777.chat.dao.repo.UserRepo;
import com.izobretatel777.chat.dto.ContactRequestDto;
import com.izobretatel777.chat.dto.ContactResponseDto;
import com.izobretatel777.chat.mapper.ContactMapper;
import com.izobretatel777.chat.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepo contactRepo;
    private final UserRepo userRepo;
    private final ContactMapper contactMapper;

    @Override
    public List<Long> getContacts() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Long userId = userRepo.findIdByLogin(currentPrincipalName);
        return contactRepo.findIdByOwnerId(userId);
    }

    @Override
    public ContactResponseDto getContactById(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Long userId = userRepo.findIdByLogin(currentPrincipalName);
        Contact contact = contactRepo.findByIdAndOwnerId(id, userId);
        if (contact != null)
            return contactMapper.toResponseDto(contact);
        else
            return new ContactResponseDto();
    }

    @Override
    public Long createContact(ContactRequestDto contactRequestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User currentUser = userRepo.findByLogin(currentPrincipalName);
        Contact contact = Contact.builder().login(contactRequestDto.getLogin())
                .name(contactRequestDto.getName()).surname(contactRequestDto.getSurname())
                .phoneNumber(contactRequestDto.getPhoneNumber()).owner(currentUser).build();
        return contactRepo.save(contact).getId();
    }

    @Override
    public void deleteContactById(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Long userId = userRepo.findIdByLogin(currentPrincipalName);
        if (contactRepo.findByIdAndOwnerId(id, userId) != null)
            contactRepo.deleteById(id);
    }
}
