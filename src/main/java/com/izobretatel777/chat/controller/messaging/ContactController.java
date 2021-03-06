package com.izobretatel777.chat.controller.messaging;

import com.izobretatel777.chat.dto.messaging.ContactRequestDto;
import com.izobretatel777.chat.dto.messaging.ContactResponseDto;
import com.izobretatel777.chat.service.messaging.ContactService;
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
    @Operation(
            summary = "Get all Contact IDs",
            description = "Get all Contact IDs for current user",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public List<Long> getContacts() {
        return contactService.getContacts();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(
            summary = "Get info about a Contact",
            description = "Get info about a Contact by its ID. User should poses this contact",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public ContactResponseDto getChatById(@PathVariable long id) {
        return contactService.getContactById(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    @Operation(
            summary = "Create a new Contact",
            description = "Create a new Contact with the user whose ID in requestDTO",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public long createChat(@RequestBody ContactRequestDto contactRequestDto) {
        return contactService.createContact(contactRequestDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(
            summary = "Delete a Contact",
            description = "Delete a Contact by its ID. User should poses this contact",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public void deleteChatById(@PathVariable long id) {
        contactService.deleteContactById(id);
    }
    
}
