package com.izobretatel777.chat.controller;

import com.izobretatel777.chat.dto.ContactRequestDto;
import com.izobretatel777.chat.dto.ContactResponseDto;
import com.izobretatel777.chat.service.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("contacts")
@Tag(name = "Contacts", description = "Controller for CRUD manipulations on contacts")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @GetMapping()
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Get all contact ids for current user", security = @SecurityRequirement(name = "bearerAuth"))
    List<Long> getContacts() {
        return contactService.getContacts();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Get info about contact by id", security = @SecurityRequirement(name = "bearerAuth"))
    ContactResponseDto getChatById(@PathVariable long id) {
        return contactService.getContactById(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Create new contact", security = @SecurityRequirement(name = "bearerAuth"))
    long createChat(@RequestBody ContactRequestDto contactRequestDto) {
        return contactService.createContact(contactRequestDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Delete contact", security = @SecurityRequirement(name = "bearerAuth"))
    void deleteChatById(@PathVariable long id) {
        contactService.deleteContactById(id);
    }
    
}
